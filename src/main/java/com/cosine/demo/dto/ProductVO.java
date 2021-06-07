package com.cosine.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @ClassName ProductVO
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/7 11:13
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    @ApiModelProperty(value = "商品id", required = true, example = "2021060300010001")
    @NotNull(message = "商品id不能为空")
    private BigInteger productId;

    @ApiModelProperty(value = "商品名称", required = true, example = "哇哈哈矿泉水")
    @NotNull(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "商品类目id", required = true, example = "1000")
    @NotNull(message = "商品类目id")
    private Integer productItemId;

    @ApiModelProperty(value = "商品单价", required = true, example = "3")
    @NotNull(message = "商品单价不能为空")
    private Double price;

    @ApiModelProperty(value = "创建工厂", required = true, example = "立方")
    @NotNull(message = "创建工厂不能为空")
    private String createFactory;
}
