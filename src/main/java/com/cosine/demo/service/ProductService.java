package com.cosine.demo.service;

import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductPurchaseDTO;

/**
 * 类描述：商品 Service接口
 *
 * @ClassName ProductService
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
public interface ProductService {
    /**
     * 新增商品
     * @param product 商品
     * @return String
     */
    String addProduct(Product product);

    /**
     * 减库存
     * @param purchaseDTO 商品dto
     * @return String
     */
    String reduceProductNum(ProductPurchaseDTO purchaseDTO);
}
