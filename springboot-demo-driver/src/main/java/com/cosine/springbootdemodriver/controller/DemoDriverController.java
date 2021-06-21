package com.cosine.springbootdemodriver.controller;

import com.cosine.springbootdemodriver.entity.ProductDTO;
import com.cosine.springbootdemodriver.service.DemoDriverService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoDriverController
 * @Description 驱动模块controller
 * @Author cosine
 * @Date 2021/6/18 15:06
 * @Version 1.0
 */
@Api(tags = "驱动模块")
@RestController
@RequestMapping("/demo-driver")
public class DemoDriverController {
    @Autowired
    private DemoDriverService demoDriverService;

    static final Logger logger = LoggerFactory.getLogger(DemoDriverController.class);

    @PostMapping("/produce")
    public String produce(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        logger.info("生产一个商品"+productDTO.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        String str = demoDriverService.produce(productDTO);
        if (str.equals("成功")) {
            return "成功";
        }
        return "失败";
    }
}
