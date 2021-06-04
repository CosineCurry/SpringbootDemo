package com.cosine.demo.dao;

import com.cosine.demo.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 商品 DAO 接口类
 * @Author cosine
 * @Date 2021/5/19 10:40
 *
 */
public interface ProductDao {
    /**
     * 插入一个商品
     * @param product 商品
     * @return int 1为成功插入，0插入失败
     */
    int insert(Product product);

    /**
     * 逻辑删除一个商品
     * @param itemId
     * @param number
     * @return 成功条数
     */
    int updateStatus(@Param("itemId") int itemId, @Param("number") int number);

    /**
     * 根据商品id搜索商品
     * @param productIdList
     * @return 成功条数
     */
    int searchProduct(@Param("productIdList") ArrayList<BigInteger> productIdList);

    /**
     * 根据商品id逻辑删除商品
     * @param productIdList
     * @return 成功条数
     */
    int updateStatusById(@Param("productIdList") ArrayList<BigInteger> productIdList);
}
