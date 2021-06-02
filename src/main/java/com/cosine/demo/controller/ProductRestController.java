package com.cosine.demo.controller;

import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Product;
import com.cosine.demo.service.ProductService;
import com.cosine.demo.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private StoreService storeService;

    static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    /**
     * 新增一个商品
     * @param product
     * @param bindingResult
     * @return
     */
    @PostMapping("/addProduct")
    public ResResult addProduct(@RequestBody Product product, BindingResult bindingResult) {
        logger.info("新增一个商品"+product.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            return ResResultUtil.error(301, bindingResult.getFieldError().getDefaultMessage());
        }
        String str = productService.addProduct(product);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"插入数据失败");
    }

    /**
     * 生产商品
     * @param productId
     * @param number
     * @return
     */
    @ApiOperation(value = "生产接口",notes = "生产接口的说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品id", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "生产数量", dataType = "String", paramType = "path")
    })
    @GetMapping("/produce/{productId}/{number}")
    public ResResult produce(@PathVariable(value = "productId") int productId, @PathVariable(value = "number") int number) {
        logger.info("更新id为"+productId+"的库存");
        String str = storeService.updateCount(productId, number);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"更新库存失败");
    }

    /**
     * 消费商品
     * @param productId
     * @param number
     * @return
     */
    @ApiOperation(value = "消费接口",notes = "消费接口的说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品id", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "消费数量", dataType = "String", paramType = "path")
    })
    @GetMapping("/consume/{productId}/{number}")
    public ResResult consume(@PathVariable(value = "productId") int productId, @PathVariable(value = "number") int number) {
        logger.info("更新id为"+productId+"的库存");
        String str = storeService.updateCount(productId, -number);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"更新库存失败");
    }

}
