package com.cosine.demo.controller;

import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductPurchaseDTO;
import com.cosine.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductRestController
 * @Description 商品Controller 实现Restful HTTP服务
 * @Author cosine
 * @Date 2021/5/28 11:07
 * @Version 1.0
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    /**
     * 新增一条商品记录
     * @param product
     * @return 插入数据状态
     */
    @ApiOperation(value = "新增一条商品记录", notes = "新增一条商品记录")
    @PostMapping("/addProduct")
    public ResResult addProduct(@RequestBody Product product) {
        logger.info("插入一条Product数据："+ product.toString());
        //数据校验
        if (product.getName() == null) {
            return ResResultUtil.error(301,"商品名称不能为空");
        }
        if (product.getNumber() < 0) {
            return ResResultUtil.error(301,"商品库存不能为负数");
        }
        String str = productService.addProduct(product);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"插入数据失败");
    }

    /**
     * 减库存
     * @param purchaseDTO 购买商品的信息
     * @return 减库存状态
     */
    @ApiOperation(value = "减库存")
    @PostMapping("/reduceProductNum")
    public ResResult reduceProductNum(@RequestBody ProductPurchaseDTO purchaseDTO) {
        logger.info(purchaseDTO.toString());
        //简单数据校验
        if (purchaseDTO.getCount() == null || purchaseDTO.getProductId() == null || purchaseDTO.getCount() < 0) {
            return ResResultUtil.error(301,"商品数据不合理");
        }
        String str = productService.reduceProductNum(purchaseDTO);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"减库存失败");
    }
}
