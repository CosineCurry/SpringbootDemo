package com.cosine.springbootdemoproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName Product
 * @Description 商品实体类
 * @Author cosine
 * @Date 2021/6/17 15:56
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private BigInteger productId;

    private String name;

    private BigDecimal price;

    private Integer itemId;

    private String createFactory;

    private Date createTime;

    private int status;

    private int version;
}
