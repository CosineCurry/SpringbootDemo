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
     * @param productId 新的库存
     * @param count 商品新的库存
     * @return int 操作结果
     */
    int updateNumber(@Param("productId") int productId, @Param("count") int count);

    /**
     * 搜索库存
     * @param productId 商品id
     * @return 库存
     */
    int getNumber(@Param("productId") int productId);
}
