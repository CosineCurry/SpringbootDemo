package com.cosine.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
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
@NacosPropertySource(dataId = "demo", autoRefreshed = true)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
