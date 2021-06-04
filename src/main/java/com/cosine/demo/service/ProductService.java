package com.cosine.demo.service;

import com.cosine.demo.dto.ProductConsumeDTO;
import com.cosine.demo.dto.ProductDTO;

import java.util.List;

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
     * 根据商品类目消费若干商品
     * @param itemIt 类目id
     * @param number 商品数量
     * @return
     */
    String consumeByItem(int itemIt, int number);

    /**
     * 根据商品id消费若干商品
     * @param productConsumeDTOs
     * @return
     */
    String consumeProducts(List<ProductConsumeDTO> productConsumeDTOs);

}
