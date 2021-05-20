package com.cosine.demo.controller;

import com.cosine.demo.domain.Order;
import com.cosine.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 类描述：
 *
 * @ClassName OrderRestController
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/19 10:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    /**
     * @Description:插入一条数据
     * @Param: [order]
     * @return: java.lang.String
     * @Author: cosine
     * @Date: 2021/5/20
     */
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        System.out.println(order);
        return orderService.addOrder(order);
    }

    @GetMapping("/deleteOrder/{orderId}")
    public String deleteOneOrder(@PathVariable(value = "orderId") int orderId) {
        return orderService.deleteOrderById(orderId);
    }

}
