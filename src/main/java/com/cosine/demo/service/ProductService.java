package com.cosine.demo.service;

import com.cosine.demo.dto.ProductDTO;

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
     * @param productDTO
     * @return
     */
    String addProduct(ProductDTO productDTO);

    /**
     * 消费若干商品
     * @param itemIt 类目id
     * @param number 商品数量
     * @return
     */
    String consumeProducts(int itemIt, int number);


}
