package com.cosine.demo.coupon;

/**
 * @ClassName ZJCouponDiscount
 * @Description 直减优惠，最低支付金额一元
 * @Author cosine
 * @Date 2021/6/8 13:54
 * @Version 1.0
 */
public class ZJCouponDiscount implements Strategy<Double> {

    @Override
    public Double calculateActualPrice(Double couponInfo, Double price) {
        Double res = price - couponInfo;
        return res > 1 ? res : 1;
    }
}
