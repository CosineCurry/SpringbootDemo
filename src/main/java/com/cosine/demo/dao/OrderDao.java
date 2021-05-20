package com.cosine.demo.dao;

import com.cosine.demo.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * 订单 DAO 接口类
 * @Author cosine
 * @Date 2021/5/19 10:40
 *
 */
public interface OrderDao {

    /**
     * 插入一条数据
     *
     * @param order 订单
     * @return int 1为成功插入，0插入失败
     */
    int insert(Order order);

    /**
     * 删除一条数据，逻辑删除，将deletestatus设置为1
     *
     * @param orderId 订单id
     * @return int 1成功删除，0删除失败
     */
    int delete(@Param("orderId") int orderId);
}
