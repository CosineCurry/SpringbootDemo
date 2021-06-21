package com.cosine.springbootdemoproduct.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.cosine.springbootdemoproduct.entity.ProductDTO;
import com.cosine.springbootdemoproduct.service.DemoProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @ClassName DemoProductController
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/17 15:54
 * @Version 1.0
 */
@Api(tags = "商品管理")
@RestController
@RequestMapping("/demo-product")
public class DemoProductController {
    @Autowired
    private DemoProductService demoProductService;
    @NacosInjected
    private NamingService namingService;

    static final Logger logger = LoggerFactory.getLogger(DemoProductController.class);

    /**
     * 在bean初始化前做一些事，注册一个服务
     */
    @PostConstruct
    public void init() {
        try {
            namingService.registerInstance("product-provider","localhost",9091);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产一个商品
     * @param productDTO 商品DTO
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增商品接口",notes = "新增商品接口的说明")
    @PostMapping("/produce")
    public String addProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        logger.info("新增一个商品"+productDTO.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        String str = demoProductService.addProduct(productDTO);
        if (str.equals("成功")) {
            return "成功";
        }
        return "插入数据失败";
    }
}
