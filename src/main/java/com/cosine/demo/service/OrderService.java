package com.cosine.demo.service;

import com.cosine.demo.domain.Order;

/**
 * 类描述：订单 Service类
 *
 * @ClassName OrderService
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
public interface OrderService {
    /**
     * 新增订单
     *
     * @param order Order类
     * @return Result<XxxxDO>
     */
    String addOrder(Order order);

    /**
     * 根据订单id删除一个订单
     *
     * @param orderId 订单id
     * @return Result<XxxxDO>
     */
    String deleteOrderById(int orderId);
}
