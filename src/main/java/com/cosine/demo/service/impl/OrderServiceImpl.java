package com.cosine.demo.service.impl;

import com.cosine.demo.common.CommonUtil;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.OrderDao;
import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderInfo;
import com.cosine.demo.dto.OrderQueryDTO;
import com.cosine.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Order findOrderById(int orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public PageInfo<Order> findAllOrderByPage(int page, int offset) {
        //这句是核心
        PageHelper.startPage(page, offset);
        List<Order> all = orderDao.findAllByPage();
        return new PageInfo<>(all);
    }

    @Override
    public OrderInfo<Order> findOrderWithCondition(OrderQueryDTO queryDTO) {
        List<Order> list = orderDao.findByCondition(queryDTO);
        long total = orderDao.countTotalNum(queryDTO);
        //在controller层有过保证：查出来为空数组时返回异常码
        int pageNo = queryDTO.getPageNo(), pageSize = queryDTO.getPageSize();
        boolean[] res = CommonUtil.conditionResult(pageNo, pageSize, total);
        return new OrderInfo<>(pageNo,pageSize,total, res[0], res[1], res[2], res[3], list);
    }

    @Override
    public String updatePriceById(int orderId, long price) {
        int tmp = orderDao.updatePriceById(orderId, price);
        if (tmp == 0) {return ResResultUtil.FAIL;}
        return ResResultUtil.SUCCESS;

    }


}
