package com.cosine.demo.service.impl;

import com.cosine.demo.common.CommonUtil;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.OrderDao;
import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderPageVO;
import com.cosine.demo.dto.OrderQueryDTO;
import com.cosine.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类描述：Service实现类
 *
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public String addOrder(Order order) {
        //计算优惠，并重新设置金额。
        order.setOrderPrice(CommonUtil.calculateActualPrice(order.getOrderPrice()));
        int res = 0;
        try {
            res = orderDao.insert(order);
        } catch (Exception e) {
            logger.error("插入数据失败",e);
        }
        if (res == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }

    @Override
    public String deleteOrderById(int orderId) {
        int res = orderDao.delete(orderId);
        if (res == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }

    /**
     * 获取订单逻辑：
     * 如果缓存存在，从缓存中获取订单信息
     * 如果缓存不存在，从 DB 中获取订单信息，然后插入缓存
     */
    @Override
    public Order findOrderById(int orderId) {
        //从缓存中获取订单信息
        String key = "order_" + orderId;
        ValueOperations<String, Order> operations = redisTemplate.opsForValue();
        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            Order order = operations.get(key);
            logger.info("从缓存中获取了订单："+order.toString());
            return order;
        }
        //从DB中获取城市信息
        Order order = orderDao.findById(orderId);
        //插入缓存 后面可以设置过期时间
        operations.set(key, order, 10, TimeUnit.HOURS);
        logger.info("订单插入缓存："+order.toString());
        return order;
    }

    @Override
    public PageInfo<Order> findAllOrderByPage(int page, int offset) {
        //这句是核心
        PageHelper.startPage(page, offset);
        List<Order> all = orderDao.findAllByPage();
        return new PageInfo<>(all);
    }

    @Override
    public OrderPageVO<Order> findOrderWithCondition(OrderQueryDTO queryDTO) {
        List<Order> list = orderDao.findByCondition(queryDTO);
        long total = orderDao.countTotalNum(queryDTO);
        //在controller层有过保证：查出来为空数组时返回异常码
        int pageNo = queryDTO.getPageNo(), pageSize = queryDTO.getPageSize();
        boolean[] res = CommonUtil.conditionResult(pageNo, pageSize, total);
        return new OrderPageVO<>(pageNo,pageSize,total, res[0], res[1], res[2], res[3], list);
    }

    @Override
    public String updatePriceById(int orderId, long price) {
        orderDao.updatePriceById(orderId, price);
        return ResResultUtil.SUCCESS;

    }

    @Override
    public String updatePayStatusById(int orderId) {
        orderDao.updatePayStatusById(orderId);
        return ResResultUtil.SUCCESS;
    }


}
