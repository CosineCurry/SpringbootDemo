package com.cosine.demo.common;

import java.math.BigInteger;

/**
 * 类描述：公共的工具类，放计算优惠等方法
 *
 * @ClassName CommonUtil
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/26 18:30
 * @Version 1.0
 */
public class CommonUtil {
    /**
     * 这个方法做两件事：1、把前端传进来的“元”转为“分”；
     *                  2、根据优惠条件判定是否有优惠。
     * @param price 前端输入的价格
     * @return BigInteger 优惠后的价格（保存到数据库的数字）
     */
    public static BigInteger calculateActualPrice(BigInteger price) {
        /**
         * int的范围-2147483648 ~ 2147483647，long的范围是-9223372036854775808 （-2的63次方）~9223372036854775807 （2的63次方-1）
         * 用long是怕数值越界，数据库中金额字段是用BigInt来存，bigint范围跟java中的long一样
         */
        long p = price.intValue() * 100L;
        /** 优惠条件：如果前端输入的价格大于100元，打95折 */
        long boundary = 10000L;
        if (p > boundary) {
            p = (long) (p * 0.95);
        }
        return new BigInteger(String.valueOf(p));
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
}
