package com.cosine.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName Product
 * @Description 商品实体类
 * @Author cosine
 * @Date 2021/5/28 10:59
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id", required = true, example = "1000")
    @NotNull(message = "商品id不能为空")
    private Integer productId;

    @ApiModelProperty(value = "商品名称", required = true, example = "哇哈哈")
    @NotNull(message = "商品名称不能为空")
    private String name;

}
