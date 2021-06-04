package com.cosine.demo.service;

import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderPageVO;
import com.cosine.demo.dto.OrderQueryDTO;
import com.github.pagehelper.PageInfo;

/**
 * 类描述：订单 Service接口
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
     * @return String
     */
    String addOrder(Order order);

    /**
     * 根据订单id删除一个订单
     *
     * @param orderId 订单id
     * @return String
     */
    String deleteOrderById(int orderId);

    /**
     * 根据订单id查找一个订单
     *
     * @param orderId 订单id
     * @return String
     */
    Order findOrderById(int orderId);

    /**
     * 查找所有订单
     *
     * @param page 当前页码
     * @param offset 每页条数
     * @return PageInfo<Order>
     */
    PageInfo<Order> findAllOrderByPage(int page, int offset);

    /**
     * 根据条件查找所有订单
     *
     * @param queryDTO 查询条件
     * @return OrderInfo<Order>
     */
    OrderPageVO<Order> findOrderWithCondition(OrderQueryDTO queryDTO);

    /**
     * 根据订单id修改订单的价格
     *
     * @param orderId 订单id
     * @param price 修改后的价格
     * @return String
     */
    String updatePriceById(int orderId, long price);

    /**
     * 根据id修改支付状态
     * @param orderId
     * @return String
     */
    String updatePayStatusById(int orderId);

}
