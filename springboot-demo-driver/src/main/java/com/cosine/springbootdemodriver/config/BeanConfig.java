package com.cosine.springbootdemodriver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName BeanConfig
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/18 16:18
 * @Version 1.0
 */
@Configuration
@Slf4j
public class BeanConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        log.info("初始化restTemplate模板...");
        return new RestTemplate();
    }

}
