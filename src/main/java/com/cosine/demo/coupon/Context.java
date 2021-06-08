package com.cosine.demo.coupon;

/**
 * @ClassName Context
 * @Description 策略控制类
 * @Author cosine
 * @Date 2021/6/8 14:02
 * @Version 1.0
 */
public class Context<T> {
    private Strategy<T> strategy;

    public Context(Strategy<T> strategy) {
        this.strategy = strategy;
    }

    public Double calculateActualPrice(T couponInfo, Double price) {
        return strategy.calculateActualPrice(couponInfo, price);
    }
}
