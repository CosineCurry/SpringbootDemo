package com.cosine.demo.controller;

import com.cosine.demo.domain.Order;
import com.cosine.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 类描述：订单Controller 实现Restful HTTP服务
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


    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        System.out.println(order);
        return orderService.addOrder(order);
    }

    @GetMapping("/deleteOrder/{orderId}")
    public String deleteOneOrder(@PathVariable(value = "orderId") int orderId) {
        return orderService.deleteOrderById(orderId);
    }

    @GetMapping("/findOneOrder/{orderId}")
    public Order findOrderById(@PathVariable(value = "orderId") int orderId) {return orderService.findOrderById(orderId);}
}
