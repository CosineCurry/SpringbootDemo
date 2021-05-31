package com.cosine.demo.service;

import com.cosine.demo.domain.Order;
import com.cosine.demo.dto.OrderInfo;
import com.cosine.demo.dto.OrderQueryDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

//单元测试回滚
@SpringBootTest
@Rollback
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    static final Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    /** 在所有测试前都会执行 */
    @BeforeAll
    public static void beforeAll() {
        logger.info("所有测试前执行的方法");
    }

    @Test
    @DisplayName("测试service层的addOrder方法")
    void addOrder() {
        logger.info("execute addOrder");
        Order order = new Order(31, new BigInteger(String.valueOf(100)), new Date(), "test", 0, 0, 10001, 3);
        orderService.addOrder(order);
    }

    @Test
    void deleteOrderById() {
    }

    @Test
    void findOrderById() {
    }

    @Test
    void findAllOrderByPage() {
    }

    @Test
    @DisplayName("测试service层的findOrderWithCondition方法")
    void findOrderWithCondition() {
        logger.info("execute findOrderWithCondition");
        //controller层有过简单的数据校验，到service层pagesize跟pageno必须有正值
        assertAll("使用断言测试",
                () -> assertTrue(orderService.findOrderWithCondition(new OrderQueryDTO(null, null, null, null, null, null, null, 1, 3)) instanceof OrderInfo),
                () -> assertTrue(orderService.findOrderWithCondition(new OrderQueryDTO(null, null, null, null, null, null, 0, 1, 3)) instanceof OrderInfo),
                () -> assertTrue(orderService.findOrderWithCondition(new OrderQueryDTO(new Integer[]{1,2}, null, null, null, null, null, 0, 1, 3)) instanceof OrderInfo),
                () -> assertTrue(orderService.findOrderWithCondition(new OrderQueryDTO(new Integer[]{1,2}, new String[]{"测试订单"}, null, null, null, null, 0, 1, 3)) instanceof OrderInfo)
                );
    }

    @Test
    void updatePriceById() {
    }
}