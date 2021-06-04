package com.cosine.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ClassName Product
 * @Description 商品实体类
 * @Author cosine
 * @Date 2021/5/28 10:59
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger productId;

    private String name;

    private Double price;

    private Integer itemId;

    private String createFactory;

    private Date createTime;

    private int status;

    private int version;

}
