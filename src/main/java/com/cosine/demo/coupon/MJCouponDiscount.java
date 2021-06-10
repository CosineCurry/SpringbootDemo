package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName MJCouponDiscount
 * @Description 满减优惠
 *              1.判断满足x元后-n元，否则不减
 *              2.最低支付金额1元
 * @Author cosine
 * @Date 2021/6/8 13:43
 * @Version 1.0
 */
public class MJCouponDiscount implements Strategy<String[]> {

    @Override
    public BigDecimal calculateActualPrice(String[] couponInfo, BigDecimal price) {
        BigDecimal x = new BigDecimal(couponInfo[0]);
        BigDecimal n = new BigDecimal(couponInfo[1]);
        //小于商品金额条件直接返回
        if (price.compareTo(x) < 0) {
            return price;
        } else {
            BigDecimal res = price.subtract(n);
            return res.compareTo(BigDecimal.ONE) > 0 ? res :BigDecimal.ONE;
        }
    }
}
