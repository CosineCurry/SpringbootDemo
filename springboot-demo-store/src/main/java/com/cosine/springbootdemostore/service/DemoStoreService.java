package com.cosine.springbootdemostore.service;

import org.apache.ibatis.annotations.Param;

public interface DemoStoreService {
    /**
     * 库存加1
     * @param itemId
     * @return
     */
    String plusOne(@Param("itemId") int itemId);
}
