package com.cosine.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName OrderQueryDTO
 * @Description 查询参数条件DTO
 * @Author cosine
 * @Date 2021/5/24 10:35
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryDTO {
    /** 批量订单id查询 **/
    private Integer[] settleOrderIdArray;
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

}
