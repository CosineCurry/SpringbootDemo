package com.cosine.springbootdemoproduct.service;

import com.cosine.springbootdemoproduct.entity.ProductDTO;

public interface DemoProductService {
    /**
     * 新增一个商品
     * @param productDTO
     * @return
     */
    String addProduct(ProductDTO productDTO);
}
