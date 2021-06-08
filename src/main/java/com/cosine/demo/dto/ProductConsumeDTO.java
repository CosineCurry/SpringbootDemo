package com.cosine.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName ProductConsumeDTO
 * @Description 用户购买商品DTO类
 * @Author cosine
 * @Date 2021/6/4 14:14
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductConsumeDTO {

    @ApiModelProperty(value = "用户id", required = true, example = "1")
    @NotNull(message = "用户id不能为空")
    private BigInteger userId;

    @ApiModelProperty(value = "折扣类型", required = true, example = "1")
    @NotNull(message = "折扣类型不能为空")
    private Integer discountType;

    @NotNull(message = "所购商品不能为空")
    private List<ProductDTO> productDTOS;
}
