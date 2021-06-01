package com.cosine.demo.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 *
 * @ClassName ProductPurchaseDTO
 * @Description 商品购买实体类
 * @Author cosine
 * @Date 2021/5/28 14:53
 * @Version 1.0
 */
@Data
public class ProductPurchaseDTO {
    private BigInteger productId;
    private Integer count;
}
