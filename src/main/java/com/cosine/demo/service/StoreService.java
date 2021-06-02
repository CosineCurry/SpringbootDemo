package com.cosine.demo.service;

import com.cosine.demo.domain.Store;

/**
 * @ClassName StoreService
 * @Description 库存 Service接口
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
public interface StoreService {
    /**
     * 新增一条库存记录
     * @param store
     * @return
     */
    String addStore(Store store);

    /**
     * 更新库存
     * @param productId
     * @param count 可正可负
     * @return
     */
    String updateCount(int productId, int count);
}
