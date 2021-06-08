package com.cosine.demo.controller;

import com.cosine.demo.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ConsumeController
 * @Description 消费商品的controller
 * @Author cosine
 * @Date 2021/6/7 15:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/consume/")
public class ConsumeController {
    @Autowired
    private ProductDao productDao;
    /**
     * 消费者首页
     * @param model
     * @return
     */
    @RequestMapping("consumeHome")
    public String produceForm(Model model) {
        model.addAttribute("products", productDao.findAllByPage());
        return "consumeMain";
    }
}
