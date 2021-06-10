package com.cosine.demo.controller;

import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderPageVO;
import com.cosine.demo.dto.OrderQueryDTO;
import com.cosine.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @ClassName OrderRestController
 * @Description 订单Controller 实现Restful HTTP服务
 * @Author cosine
 * @Date 2021/5/19 10:41
 * @Version 1.0
 */
@Api(tags = "订单管理")
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
    @ApiOperation(value = "添加订单", notes = "添加订单")
    @PostMapping("/addOrder")
    public ResResult addOrder(@RequestBody @Valid Order order, BindingResult bindingResult) {
        logger.info("插入一条Order数据："+ order.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return ResResultUtil.error(301, message);
        }
        String str = orderService.addOrder(order);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"插入数据失败");
    }

    /**
     * 根据id删除数据
     * @param orderId
     * @return String 删除状态
     */
    @ApiOperation(value = "根据id删除订单")
    @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "String", paramType = "path")
    @GetMapping("/deleteOrder/{orderId}")
    public ResResult deleteOneOrder(@PathVariable(value = "orderId") int orderId) {
        logger.info("删除id为%d的订单",orderId);
        String str = orderService.deleteOrderById(orderId);
        if (str.equals(ResResultUtil.FAIL)) {
            return ResResultUtil.error(303,"删除数据失败");
        }
        return ResResultUtil.success();
    }

    /**
     * 根据id查找订单
     * @param orderId
     * @return Order 订单
     */
    @ApiOperation(value = "根据id查找订单")
    @ApiImplicitParam(name = "orderId", value = "orderId", dataType = "String", paramType = "path")
    @GetMapping("/findOneOrder/{orderId}")
    public ResResult findOrderById(@PathVariable(value = "orderId") int orderId) {
        logger.info("查找id为%d的订单",orderId);
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            return ResResultUtil.error(304, "无记录");
        }
        return ResResultUtil.success(order);
    }

    /**
     * 利用插件PageHelper分页查询
     * @param page 当前页码
     * @param offset 页容量
     * @return PageInfo<Order> PageHelper封装的订单数组
     */
    @ApiOperation(value = "插件分页查询")
    @GetMapping("/findAllOrderByPageHelper")
    public ResResult findAllOrderByPage(@RequestParam(value = "当前页码") int page, @RequestParam(value = "每页数量") int offset) {
        return ResResultUtil.success(orderService.findAllOrderByPage(page, offset));
    }

    /**
     * 根据查询条件分页查询订单
     * @param queryDTO 查询条件具体类
     * @return String 封装好的订单数组
     */
    @ApiOperation(value = "根据查询条件分页查询订单")
    @PostMapping("/findOrderWithCondition")
    public ResResult findOrderWithCondition(@RequestBody OrderQueryDTO queryDTO) {
        logger.info("查找条件为："+queryDTO.toString());
        if (queryDTO.getPageNo() == null || queryDTO.getPageNo() < 0 || queryDTO.getPageSize() < 0) {
            return ResResultUtil.error(301,"查询数据不正确");
        }
        //设置pageSize默认值为5
        if (queryDTO.getPageSize() == null) {
            queryDTO.setPageSize(5);
        }
        OrderPageVO<Order> orderPageVO = orderService.findOrderWithCondition(queryDTO);
        if (orderPageVO.getList().isEmpty()) {
            return ResResultUtil.error(304,"无记录");
        }
        return ResResultUtil.success(orderPageVO);
    }

    /**
     * 更新价格
     * @param orderId 订单id
     * @param price 修改后的订单价格
     * @return String 修改状态
     */
    @ApiOperation(value = "修改订单价格")
    @GetMapping("/updatePrice")
    public ResResult changePriceById(@RequestParam(value = "orderId") int orderId, @RequestParam(value = "price") String price) {
        String str = orderService.updatePriceById(orderId, new BigDecimal(price));
        if (str.equals(ResResultUtil.FAIL)) {
            return ResResultUtil.error(305,"修改价格失败");
        }
        return ResResultUtil.success();
    }
}
