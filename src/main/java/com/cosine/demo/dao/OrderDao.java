package com.cosine.demo.dao;

import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

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

    /**
     * 根据订单id查找订单
     *
     * @param orderId 订单id
     * @return Order 具体订单
     */
    Order findById(@Param("orderId") int orderId);

    /**
     * 查找所有订单
     *
     * @return List<Order> 订单list
     */
    List<Order> findAllByPage();

    /**
     * 根据条件查找订单
     *
     * @param queryDTO 查询条件
     * @return List<Order> 订单list
     */
    List<Order> findByCondition(OrderQueryDTO queryDTO);

    /**
     * 查出总数据量
     *
     * @param queryDTO 查询条件
     * @return long 总数
     */
    long countTotalNum(OrderQueryDTO queryDTO);


    /**
     * 修改订单的价格
     *
     *@param orderId 订单id
     *@param price 修改后的订单价格
     * @return int 1为成功，0为失败
     */
    int updatePriceById(@Param("orderId") int orderId, @Param("price") BigDecimal price);

    /**
     * 修改订单的支付状态
     *
     *@param orderId 订单id
     * @return int 1为成功，0为失败
     */
    int updatePayStatusById(@Param("orderId") int orderId);
}
