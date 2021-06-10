package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName ZJCouponDiscount
 * @Description 直减优惠，最低支付金额一元
 * @Author cosine
 * @Date 2021/6/8 13:54
 * @Version 1.0
 */
public class ZJCouponDiscount implements Strategy<Double> {

    @Override
    public BigDecimal calculateActualPrice(Double couponInfo, BigDecimal price) {
        BigDecimal x = new BigDecimal(couponInfo);
        BigDecimal res = price.subtract(x);
        return res.compareTo(BigDecimal.ONE) > 0 ? res :BigDecimal.ONE;
    }
}
