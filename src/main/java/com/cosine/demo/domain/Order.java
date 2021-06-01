package com.cosine.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @ClassName Order
 * @Description 实体类，订单类
 * @Author cosine
 * @Date 2021/5/19 10:40
 * @Version 1.0
 */
@ApiModel(value = "订单对象", description = "订单对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "订单id", required = true, example = "1000")
    @NotNull(message = "订单id不能为空")
    private Integer orderId;

    @ApiModelProperty(value = "价格", required = true, example = "1000")
    @Range(min = 0, message = "价格必须为正数")
    private BigInteger orderPrice;

    @ApiModelProperty(value = "订单日期", required = true, example = "2020-12-12 4:30:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;

    @ApiModelProperty(value = "订单", required = true, example = "测试订单")
    @NotNull(message = "订单名称不能为空")
    private String orderTitle;

    /** 支付状态 默认为0 */
    @ApiModelProperty(value = "支付状态", example = "1")
    private int orderPayStatus;

    /** 删除状态 默认为0 */
    @ApiModelProperty(value = "删除状态", example = "0")
    private int orderDeleteStatus;

    @ApiModelProperty(value = "商品id", required = true, example = "1000")
    @NotNull(message = "商品id不能为空")
    private Integer productId;

    @ApiModelProperty(value = "商品数量", required = true, example = "1000")
    @NotNull(message = "商品数量不能为空")
    private Integer productCount;
}
