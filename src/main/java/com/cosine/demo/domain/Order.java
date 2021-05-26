package com.cosine.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 类描述：实体类，订单类
 *
 * @ClassName Order
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
public class Order {
    /** 订单id */
    private Integer orderId;
    /** 价格 */
    private BigInteger orderPrice;
    /** 订单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;
    /** 订单名称 */
    private String orderTitle;
    /** 支付状态 默认为0 */
    private int orderPayStatus;
    /** 删除状态 默认为0 */
    private int orderDeleteStatus;

    public Order() {}
    public Order(Integer orderId, BigInteger orderPrice, Date orderTime, String orderTitle, int orderPayStatus, int orderDeleteStatus) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.orderTime = orderTime;
        this.orderTitle = orderTitle;
        this.orderPayStatus = orderPayStatus;
        this.orderDeleteStatus = orderDeleteStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigInteger getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigInteger orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public int getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(int orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public int getOrderDeleteStatus() {
        return orderDeleteStatus;
    }

    public void setOrderDeleteStatus(int orderDeleteStatus) {
        this.orderDeleteStatus = orderDeleteStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderPrice=" + orderPrice +
                ", orderTime=" + orderTime +
                ", orderTitle='" + orderTitle + '\'' +
                ", orderPayStatus=" + orderPayStatus +
                ", orderDeleteStatus=" + orderDeleteStatus +
                '}';
    }
}
