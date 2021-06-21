package com.cosine.springbootdemostore.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName Store
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/18 13:49
 * @Version 1.0
 */
@Data
public class Store {
    /** 库存id，自增属性 */
    private int storeId;
    @NotNull(message = "库存名称不能为空")
    private String name;
    private int count;
    @NotNull(message = "类目id不能为空")
    private Integer itemId;
    @NotNull(message = "库存最大值不能为空")
    private Integer maxCount;
    private int version;
}
