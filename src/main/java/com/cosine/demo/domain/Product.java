package com.cosine.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 类描述：商品类
 *
 * @ClassName Product
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/28 10:59
 * @Version 1.0
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 商品id，在数据库中是自增属性 */
    private BigInteger productId;
    /** 商品名称 */
    private String name;
    /** 商品库存 */
    private int number;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
