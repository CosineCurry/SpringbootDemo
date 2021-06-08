package com.cosine.demo.coupon;

/**
 * @ClassName ZKCouponDiscount
 * @Description 折扣优惠，最低支付金额1元
 * @Author cosine
 * @Date 2021/6/8 14:00
 * @Version 1.0
 */
public class ZKCouponDiscount implements Strategy<Double> {
    @Override
    public Double calculateActualPrice(Double couponInfo, Double price) {
        Double res = price * couponInfo;
        return res > 1 ? res :1;
    }
}
