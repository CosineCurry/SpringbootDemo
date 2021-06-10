package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName ZKCouponDiscount
 * @Description 折扣优惠，最低支付金额1元，保留两位小数
 * @Author cosine
 * @Date 2021/6/8 14:00
 * @Version 1.0
 */
public class ZKCouponDiscount implements Strategy<Double> {
    @Override
    public BigDecimal calculateActualPrice(Double couponInfo, BigDecimal price) {
        BigDecimal x = BigDecimal.valueOf(couponInfo);
        //四舍五入，保留两位小数
        BigDecimal res = price.multiply(x.setScale(2, BigDecimal.ROUND_HALF_UP));
        return res.compareTo(BigDecimal.ONE) > 0 ? res :BigDecimal.ONE;
    }
}
