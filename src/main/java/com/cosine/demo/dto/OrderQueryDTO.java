package com.cosine.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName OrderQueryDTO
 * @Description 查询参数条件DTO
 * @Author cosine
 * @Date 2021/5/24 10:35
 * @Version 1.0
 */

public class OrderQueryDTO {
    /** 批量订单id查询 **/
    private Integer[] settleOrderIdArray;
    /** 批量订单名称查询 **/
    private String[] orderTitleArray;
    /** 订单金额范围查询 **/
    private BigInteger orderPriceStart;
    private BigInteger orderPriceEnd;
    /** 日期范围查询 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTimeEnd;
    /** 支付状态查询 **/
    private Integer orderPayStatus;
    /** 第几页 **/
    private Integer pageNo;
    /** 每页容量 **/
    private Integer pageSize;
    /** 每页容量 **/
    private Integer offset;

    public OrderQueryDTO() {}
    public OrderQueryDTO(Integer[] settleOrderIdArray, String[] orderTitleArray, BigInteger orderPriceStart, BigInteger orderPriceEnd, Date orderTimeStart, Date orderTimeEnd, Integer orderPayStatus, Integer pageNo, Integer pageSize) {
        this.settleOrderIdArray = settleOrderIdArray;
        this.orderTitleArray = orderTitleArray;
        this.orderPriceStart = orderPriceStart;
        this.orderPriceEnd = orderPriceEnd;
        this.orderTimeStart = orderTimeStart;
        this.orderTimeEnd = orderTimeEnd;
        this.orderPayStatus = orderPayStatus;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer[] getSettleOrderIdArray() {
        return settleOrderIdArray;
    }

    public void setSettleOrderIdArray(Integer[] settleOrderIdArray) {
        this.settleOrderIdArray = settleOrderIdArray;
    }

    public String[] getOrderTitleArray() {
        return orderTitleArray;
    }

    public void setOrderTitleArray(String[] orderTitleArray) {
        this.orderTitleArray = orderTitleArray;
    }

    public BigInteger getOrderPriceStart() {
        return orderPriceStart;
    }

    public void setOrderPriceStart(BigInteger orderPriceStart) {
        this.orderPriceStart = orderPriceStart;
    }

    public BigInteger getOrderPriceEnd() {
        return orderPriceEnd;
    }

    public void setOrderPriceEnd(BigInteger orderPriceEnd) {
        this.orderPriceEnd = orderPriceEnd;
    }

    public Date getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(Date orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public Date getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(Date orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    public Integer getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(Integer orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        this.offset = (this.pageNo - 1) * this.pageSize;
        return this.offset;
    }

    @Override
    public String toString() {
        return "OrderQueryDTO{" +
                "settleOrderIdArray=" + Arrays.toString(settleOrderIdArray) +
                ", orderTitleArray=" + Arrays.toString(orderTitleArray) +
                ", orderPriceStart=" + orderPriceStart +
                ", orderPriceEnd=" + orderPriceEnd +
                ", orderTimeStart=" + orderTimeStart +
                ", orderTimeEnd=" + orderTimeEnd +
                ", orderPayStatus=" + orderPayStatus +
                ", pageNum=" + pageNo +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                '}';
    }
}
