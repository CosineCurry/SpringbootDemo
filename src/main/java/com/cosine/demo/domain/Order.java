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
    private BigInteger orderId;

    @ApiModelProperty(value = "价格", required = true, example = "1000")
    @Range(min = 0, message = "价格必须为正数")
    private Double orderPrice;

    @ApiModelProperty(value = "订单创建日期", required = true, example = "2020-12-12 4:30:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "订单更新日期", required = true, example = "2020-12-12 4:30:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /** 支付状态 默认为0 */
    @ApiModelProperty(value = "支付状态", example = "0")
    private int payStatus;

    /** 订单状态 0为正常，1为取消订单，默认为0 */
    @ApiModelProperty(value = "订单状态", example = "0")
    private int orderStatus;

    @ApiModelProperty(value = "用户id", required = true, example = "1000")
    @NotNull(message = "用户id不能为空")
    private BigInteger userId;

    @ApiModelProperty(value = "折扣类型", required = true, example = "0")
    @NotNull(message = "折扣类型不能为空")
    private Integer discountType;
}
