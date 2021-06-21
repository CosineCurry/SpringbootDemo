package com.cosine.springbootdemoproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableDiscoveryClient为服务发现注解
 * @author cosine
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cosine.springbootdemoproduct.dao")
public class SpringbootDemoProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoProductApplication.class, args);
    }


}
