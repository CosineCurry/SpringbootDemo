package com.cosine.demo.dao;

import com.cosine.demo.domain.Store;
import org.apache.ibatis.annotations.Param;

/**
 * 库存 DAO 接口类
 * @Author cosine
 * @Date 2021/5/19 10:40
 *
 */
public interface StoreDao {
    /**
     * 插入一条库存记录
     * @param store 库存
     * @return int 1为成功插入，0插入失败
     */
    int insert(Store store);

    /**
     * 更新库存
     * @param itemId
     * @param count 商品新的库存
     * @return int 操作结果
     */
    int updateNumber(@Param("itemId") int itemId, @Param("count") int count);

    /**
     * 搜索库存
     * @param itemId 商品类目id
     * @return 库存
     */
    int getNumber(@Param("itemId") int itemId);

    /**
     * 搜索最大库存
     * @param itemId 商品类目id
     * @return 最大库存
     */
    int getMaxCount(@Param("itemId") int itemId);
}
