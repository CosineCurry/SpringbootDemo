package com.cosine.demo.common;

import com.cosine.demo.coupon.*;
import com.cosine.demo.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CommonUtil
 * @Description 公共的工具类
 * @Author cosine
 * @Date 2021/5/26 18:30
 * @Version 1.0
 */
public class CommonUtil<T> {
    /**
     * 根据优惠条件判定是否有优惠，discountType为0时没有优惠，1满（100）减（10），2直减（10），3折扣（九折）。
     * @param discountType 打折类型
     * @param price 打折前的价格
     * @return Double 优惠后的价格（保存到数据库的数字）
     */
    public BigDecimal calculatePrice(int discountType, T couponInfo, BigDecimal price) {

        Context context = new Context(DiscountCalculationFactory.strategy(discountType));
        return context.calculateActualPrice(couponInfo, price);

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

    public static BigDecimal calculateTotalPrice(List<ProductDTO> productDTOs) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < productDTOs.size(); i++) {
            sum = sum.add(productDTOs.get(i).getPrice());
        }
        return sum;
    }
}
