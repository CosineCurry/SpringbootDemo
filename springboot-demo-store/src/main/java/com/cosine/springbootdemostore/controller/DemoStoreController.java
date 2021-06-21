package com.cosine.springbootdemostore.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.cosine.springbootdemostore.service.DemoStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @ClassName DemoStoreController
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/18 13:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/demo-store")
public class DemoStoreController {
    @Autowired
    private DemoStoreService demoStoreService;
    @NacosInjected
    private NamingService namingService;

    static final Logger logger = LoggerFactory.getLogger(DemoStoreController.class);

    /**
     * 在bean初始化前做一些事，注册一个服务
     */
    @PostConstruct
    public void init() {
        try {
            namingService.registerInstance("store-provider","localhost",9092);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/plusOne/{itemId}")
    public String plusOne(@PathVariable(value = "itemId") int itemId) {
        logger.info("库存+1");
        String str = demoStoreService.plusOne(itemId);
        if (str.equals("成功")) {
            return "成功";
        }
        return "失败";
    }
}
