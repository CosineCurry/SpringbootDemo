package com.cosine.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName PageController
 * @Description 控制页面跳转到的controller，专为自己写的前端
 * @Author cosine
 * @Date 2021/6/7 14:18
 * @Version 1.0
 */
@Controller
public class PageController {

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/consume")
    public String consume() {
        return "consumeMain";
    }
}
