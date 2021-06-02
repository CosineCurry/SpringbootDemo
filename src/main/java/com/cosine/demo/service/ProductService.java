package com.cosine.demo.service;

import com.cosine.demo.domain.Product;

/**
 * @ClassName ProductService
 * @Description 商品 Service接口
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 新增一个商品
     * @param product
     * @return
     */
    String addProduct(Product product);
}
