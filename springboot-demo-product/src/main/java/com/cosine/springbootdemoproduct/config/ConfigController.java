package com.cosine.springbootdemoproduct.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName ConfigController
 * @Description nacos配置中心
 * @Author cosine
 * @Date 2021/6/17 16:37
 * @Version 1.0
 */
@RestController
@NacosPropertySource(dataId = "example", autoRefreshed = true)
@RequestMapping("/config")
public class ConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private String useLocalCache;

    @RequestMapping("/get")
    public String get() {
        return useLocalCache;
    }

}
