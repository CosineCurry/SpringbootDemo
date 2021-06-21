package com.cosine.springbootdemoproduct.dao;

import com.cosine.springbootdemoproduct.entity.Product;

public interface DemoProductDao {
    /**
     * 插入一个商品
     * @param product 商品
     * @return int 1为成功插入，0插入失败
     */
    int insert(Product product);
}
