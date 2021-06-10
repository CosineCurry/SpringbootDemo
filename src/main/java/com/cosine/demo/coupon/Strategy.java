package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName CommonUtil
 * @Description 利用策略模式计算优惠
 * @Author cosine
 * @Date 2021/5/26 18:30
 * @Version 1.0
 */
public interface Strategy<T> {
    /**
     * 计算优惠的接口方法
     * @param price 优惠前的价格
     * @return 优惠后的价格
     */
    BigDecimal calculateActualPrice(T couponInfo, BigDecimal price);

}
