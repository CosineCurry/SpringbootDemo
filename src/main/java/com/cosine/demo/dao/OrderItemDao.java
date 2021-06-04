package com.cosine.demo.dao;

import com.cosine.demo.domain.OrderItem;

/**
 * 订单项 DAO 接口类
 * @Author cosine
 * @Date 2021/5/19 10:40
 *
 */
public interface OrderItemDao {
    /**
     * 插入一条数据
     * @param orderItem 订单项
     * @return int 1为成功插入，0插入失败
     */
    int insert(OrderItem orderItem);
}
