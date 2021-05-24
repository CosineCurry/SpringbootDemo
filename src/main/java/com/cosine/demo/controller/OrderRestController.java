package com.cosine.demo.controller;

import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderInfo;
import com.cosine.demo.dto.OrderQueryDTO;
import com.cosine.demo.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    /**
     * 新增一条订单记录
     * @param order
     * @return Sting 插入数据状态
     */
    @PostMapping("/addOrder")
    public String addOrder(@RequestBody Order order) {
        logger.info("插入一条Order数据："+order.toString());
        return orderService.addOrder(order);
    }

    /**
     * 根据id删除数据
     * @param orderId
     * @return String 删除状态
     */
    @GetMapping("/deleteOrder/{orderId}")
    public String deleteOneOrder(@PathVariable(value = "orderId") int orderId) {
        logger.info("删除id为%d的订单",orderId);
        return orderService.deleteOrderById(orderId);
    }

    /**
     * 根据id查找订单
     * @param orderId
     * @return 订单
     */
    @GetMapping("/findOneOrder/{orderId}")
    public Order findOrderById(@PathVariable(value = "orderId") int orderId) {
        logger.info("查找id为%d的订单",orderId);
        return orderService.findOrderById(orderId);
    }

    /**
     * 利用插件PageHelper分页查询
     * @param page 当前页码
     * @param offset 页容量
     * @return PageHelper封装的订单数组
     */
    @GetMapping("/findAllOrderByPageHelper")
    public PageInfo<Order> findAllOrderByPage(@RequestParam(value = "当前页码") int page, @RequestParam(value = "每页数量") int offset) {
        return orderService.findAllOrderByPage(page, offset);
    }

    /**
     * 根据查询条件分页查询订单
     * @param queryDTO 查询条件具体类
     * @return 封装好的订单数组
     */
    @PostMapping("/findAllOrderWithCondition")
    public OrderInfo<Order> findAllOrderWithCondition(@RequestBody OrderQueryDTO queryDTO) {
        logger.info("查找条件为："+queryDTO.toString());
        return orderService.findAllOrderWithCondition(queryDTO);
    }

    /**
     * 更新价格
     * @param orderId 订单id
     * @param price 修改后的订单价格
     * @return 修改状态
     */
    @GetMapping("/updatePrice")
    public String changePriceById(@RequestParam(value = "orderId") int orderId, @RequestParam(value = "price") long price) {
        return orderService.updatePriceById(orderId, price);
    }
}
