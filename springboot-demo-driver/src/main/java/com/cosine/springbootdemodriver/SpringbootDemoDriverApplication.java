package com.cosine.springbootdemodriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootDemoDriverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoDriverApplication.class, args);
    }

}
