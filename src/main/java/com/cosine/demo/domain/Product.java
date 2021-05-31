package com.cosine.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    public BigInteger getProductId() {
        return productId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                '}';
    }
}
