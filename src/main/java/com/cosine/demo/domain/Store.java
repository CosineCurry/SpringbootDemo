package com.cosine.demo.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName Store
 * @Description 库存表对应的实体类
 * @Author cosine
 * @Date 2021/6/2 10:29
 * @Version 1.0
 */
@Data
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
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
