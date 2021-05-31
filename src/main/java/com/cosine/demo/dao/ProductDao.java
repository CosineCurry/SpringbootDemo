package com.cosine.demo.dao;

import com.cosine.demo.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

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
    int insertProduct(Product product);

    /**
     * 搜索库存
     * @param productId 商品id
     * @return 库存
     */
    int getNumber(@Param("productId") BigInteger productId);

    /**
     * 更新库存
     * @param num 新的库存
     * @param productId 商品id
     * @return int 操作结果
     */
    int updateNumber(@Param("num") int num, @Param("productId") BigInteger productId);
}
