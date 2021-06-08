package com.cosine.demo.coupon;

/**
 * @ClassName MJCouponDiscount
 * @Description 满减优惠
 *              1.判断满足x元后-n元，否则不减
 *              2.最低支付金额1元
 * @Author cosine
 * @Date 2021/6/8 13:43
 * @Version 1.0
 */
public class MJCouponDiscount implements Strategy<Double[]> {

    @Override
    public Double calculateActualPrice(Double[] couponInfo, Double price) {
        Double x = couponInfo[0];
        Double n = couponInfo[1];
        //小于商品金额条件直接返回
        if (price < x) {
            return price;
        } else {
            Double res = price - n;
            return  res > 1 ? res : 1;
        }
    }
}
