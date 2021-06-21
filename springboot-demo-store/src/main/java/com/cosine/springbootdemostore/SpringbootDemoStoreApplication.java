package com.cosine.springbootdemostore;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cosine.springbootdemostore.dao")
public class SpringbootDemoStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoStoreApplication.class, args);
    }

}
