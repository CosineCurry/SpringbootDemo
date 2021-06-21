package com.cosine.springbootdemostore.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Description 配置中心测试
 * @Author cosine
 * @Date 2021/6/17 16:40
 * @Version 1.0
 */
@NacosPropertySource(dataId = "example", autoRefreshed = true)
@RestController
@RequestMapping("/config")
public class ConfigController {
    @NacosValue(value = "${useLocalCache}", autoRefreshed = true)
    private String useLocalCache;

    @RequestMapping("/get")
    public String get() {
        return useLocalCache;
    }
}
