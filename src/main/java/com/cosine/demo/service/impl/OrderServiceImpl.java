package com.cosine.demo.service.impl;

import com.cosine.demo.dao.OrderDao;
import com.cosine.demo.domain.Order;
import com.cosine.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    @Override
    public String addOrder(Order order) {
        //数据校验
        if (order == null || order.getOrderTime() == null || order.getOrderPrice() == null || order.getOrderTitle() == null) {
            return "对象或者对象字段不能为空";
        }
        if (order.getOrderPrice().intValue() < 0) {
            return "价格不能为负数";
        }
        /**
         * price从“元”转为“分”
         * int的范围-2147483648 ~ 2147483647，long的范围是-9223372036854775808 （-2的63次方）~9223372036854775807 （2的63次方-1）
         * 用long是怕数值越界，数据库中金额字段是用BigInt来存，bigint范围跟java中的long一样
         */
        long p = order.getOrderPrice().intValue() * 100;
        //如果大于100元，打95折
        long couponPrice = 10000;
        if (p > couponPrice) {
            p = (long)(p * 0.95);
        }
        BigInteger price = new BigInteger(String.valueOf(p));
        order.setOrderPrice(price);

        int res = orderDao.insert(order);
        if (res == 1) {
            return "插入数据成功";
        }
        return "插入数据失败";
    }

    @Override
    public String deleteOrderById(int orderId) {
        int res = orderDao.delete(orderId);
        if (res == 1) {
            return "删除数据成功";
        }
        return "删除数据失败";
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
    public String updatePriceById(int orderId, long price) {
        String res = "修改成功";
        int tmp = orderDao.updatePriceById(orderId, price);
        if (tmp == 0) {res = "修改失败";}
        return res;

    }


}
