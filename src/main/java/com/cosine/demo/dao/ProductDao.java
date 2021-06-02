package com.cosine.demo.dao;

import com.cosine.demo.domain.Product;

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

}
