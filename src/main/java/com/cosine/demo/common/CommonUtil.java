package com.cosine.demo.common;

import com.cosine.demo.coupon.Context;
import com.cosine.demo.coupon.MJCouponDiscount;
import com.cosine.demo.coupon.ZJCouponDiscount;
import com.cosine.demo.coupon.ZKCouponDiscount;
import com.cosine.demo.dto.ProductDTO;

import java.util.List;

/**
 * @ClassName CommonUtil
 * @Description 公共的工具类
 * @Author cosine
 * @Date 2021/5/26 18:30
 * @Version 1.0
 */
public class CommonUtil {
    /**
     * 根据优惠条件判定是否有优惠，discountType为0时没有优惠，1满（100）减（10），2直减（10），3折扣（九折）。
     * @param discountType 打折类型
     * @param price 打折前的价格
     * @return Double 优惠后的价格（保存到数据库的数字）
     */
    public static Double calculatePrice(int discountType, Double price) {

        if (discountType == 0) {
            return price;
        } else if (discountType == 1) {
            Double[] array = new Double[2];
            array[0] = 100.0;
            array[1] = 10.0;
            Context<Double[]> context = new Context(new MJCouponDiscount());
            return context.calculateActualPrice(array, price);
        } else if (discountType == 2) {
            Context<Double> context = new Context<>(new ZJCouponDiscount());
            return context.calculateActualPrice(Double.valueOf(10), price);
        } else {
            Context<Double> context = new Context<>(new ZKCouponDiscount());
            return  context.calculateActualPrice(0.9, price);
        }

    }

    /**
     * 用来判断四个参数：是否为首页、是否为尾页、有无前页、有无后页
     * @param pageNo 当前页
     * @param pageSize 页容量
     * @param total 查询到的总数
     * @return 返回一个布尔值数组，顺序为：是否为首页、是否为尾页、有无前页、有无后页
     */
    public static boolean[] conditionResult(int pageNo, int pageSize, long total) {
        boolean[] res = new boolean[4];
        boolean isFirstPage = false, isLastPage = false, hasPreviousPage = false, hasNextPage = false;
        if (pageNo == 1) {
            isFirstPage = true;
        }
        if ((long) pageNo * pageSize >= total) {
            isLastPage = true;
        }
        if (pageNo > 1) {
            hasPreviousPage = true;
        }
        if (!isLastPage) {
            hasNextPage = true;
        }
        res[0] = isFirstPage;
        res[1] = isLastPage;
        res[2] = hasPreviousPage;
        res[3] = hasNextPage;
        return res;
    }

    public static Double calculateTotalPrice(List<ProductDTO> productDTOs) {
        double sum = 0;
        for (int i = 0; i < productDTOs.size(); i++) {
            sum += productDTOs.get(i).getPrice();
        }
        return sum;
    }
}
