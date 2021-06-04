package com.cosine.demo.controller;

import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dto.ProductConsumeDTO;
import com.cosine.demo.dto.ProductDTO;
import com.cosine.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
     * 生产一个商品
     * @param productDTO 商品DTO
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增商品接口",notes = "新增商品接口的说明")
    @PostMapping("/produce")
    public ResResult addProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        logger.info("新增一个商品"+productDTO.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            return ResResultUtil.error(301, bindingResult.getFieldError().getDefaultMessage());
        }
        String str = productService.addProduct(productDTO);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"插入数据失败");
    }


    /**
     * 根据商品类目消费商品
     * @param itemId 商品类目id
     * @param number 消费数量
     * @return
     */
    @ApiOperation(value = "消费接口1",notes = "消费接口1的说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId", value = "商品id", dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "number", value = "消费数量", dataType = "String", paramType = "path")
    })
    @GetMapping("/consumeByItem/{itemId}/{number}")
    public ResResult consume(@PathVariable(value = "itemId") int itemId, @PathVariable(value = "number") int number) {
        logger.info("调用消费接口");
        String str = productService.consumeByItem(itemId, number);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"更新库存失败");
    }

    /**
     * 根据商品id消费商品
     * @param productConsumeDTOs 商品列表
     * @return
     */
    @ApiOperation(value = "消费接口2", notes = "消费接口2的说明")
    @PostMapping("/consumeProducts")
    public ResResult consumeProducts(@RequestBody @Valid List<ProductConsumeDTO> productConsumeDTOs, BindingResult bindingResult) {
        //数据校验
        if (productConsumeDTOs.isEmpty() || bindingResult.hasErrors()) {
            return ResResultUtil.error(301, bindingResult.getFieldError().getDefaultMessage());
        }
        String str = productService.consumeProducts(productConsumeDTOs);
        if (str.equals(ResResultUtil.FAIL)) {
            return ResResultUtil.error(305,"购买失败");
        }
        return ResResultUtil.success();
    }

}
