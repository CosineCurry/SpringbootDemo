package com.cosine.demo.coupon;

import java.math.BigDecimal;

/**
 * @ClassName Context
 * @Description 策略控制类
 * @Author cosine
 * @Date 2021/6/8 14:02
 * @Version 1.0
 */
public class Context<T> {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal calculateActualPrice(T couponInfo, BigDecimal price) {
        return strategy.calculateActualPrice(couponInfo, price);
    }
}
