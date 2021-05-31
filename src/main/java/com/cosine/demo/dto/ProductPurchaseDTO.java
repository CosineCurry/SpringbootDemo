package com.cosine.demo.dto;

import java.math.BigInteger;

/**
 * 类描述：商品购买实体类
 *
 * @ClassName ProductPurchaseDTO
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/28 14:53
 * @Version 1.0
 */
public class ProductPurchaseDTO {
    private BigInteger productId;
    private Integer count;

    public BigInteger getProductId() {
        return productId;
    }

    public void setProductId(BigInteger productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductPurchaseDTO{" +
                "productId=" + productId +
                ", count=" + count +
                '}';
    }
}
