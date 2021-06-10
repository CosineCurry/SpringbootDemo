package com.cosine.demo.coupon;

/**
 * @ClassName DiscountCalculationFactory
 * @Description 优惠计算逻辑工厂
 * @Author cosine
 * @Date 2021/6/9 17:27
 * @Version 1.0
 */
public class DiscountCalculationFactory {
    /**
     * 根据参数类型选择优惠计算方式
     * @param discountType
     * @return
     */
    public static Strategy strategy(int discountType) {
        if (discountType == 1) {
            return new MJCouponDiscount();
        } else if (discountType == 2) {
            return new ZJCouponDiscount();
        } else if (discountType == 3) {
            return new ZKCouponDiscount();
        } else {
            return new DefaultCouponDiscount();
        }
    }
}
