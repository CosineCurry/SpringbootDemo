package com.cosine.springbootdemostore.dao;

public interface DemoStoreDao {
    /**
     * 根据类目id增加库存
     * @param itemId
     * @return
     */
    int plusOne(int itemId);

}
