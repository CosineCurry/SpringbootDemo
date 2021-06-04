package com.cosine.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @ClassName OrderItem
 * @Description 订单详情
 * @Author cosine
 * @Date 2021/6/4 10:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private BigInteger orderItemId;
    private BigInteger orderId;
    private BigInteger productId;
    private Double price;
}
