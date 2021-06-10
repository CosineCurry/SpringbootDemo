package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName DefaultCouponDiscount
 * @Description 没有优惠
 * @Author cosine
 * @Date 2021/6/9 17:34
 * @Version 1.0
 */
public class DefaultCouponDiscount implements Strategy<Double>{
    @Override
    public BigDecimal calculateActualPrice(Double couponInfo, BigDecimal price) {
        return price;
    }
}
