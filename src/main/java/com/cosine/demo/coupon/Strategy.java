package com.cosine.demo.coupon;
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
     * @param couponInfo 优惠信息
     * @param price 优惠前的价格
     * @return 优惠后的价格
     */
    public Double calculateActualPrice(T couponInfo, Double price);

}
