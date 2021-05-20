package com.cosine.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 主程序入口
 *
 * @author cosine
 * @date 2021/5/20
 */
@SpringBootApplication
@MapperScan("com.cosine.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
