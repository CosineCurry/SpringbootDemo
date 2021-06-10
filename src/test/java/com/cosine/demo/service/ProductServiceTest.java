package com.cosine.demo.service;

import com.cosine.demo.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

@SpringBootTest
//单元测试回滚
@Rollback
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * 生产者
     */
    class ProducerRunnable implements Runnable {
        @Override
        public void run() {
            //使用毫秒级别的时间戳生成商品id （long）
            long productId = System.currentTimeMillis();
            ProductDTO productDTO = new ProductDTO(new BigInteger(String.valueOf(productId)), "哇哈哈矿泉水", new BigDecimal(3),1000, Thread.currentThread().getName());
            productService.addProduct(productDTO);
            logger.info(Thread.currentThread().getName()+"生产："+1);
        }
    }
    /**
     * 消费者
     */
    class ConsumerRunnable implements Runnable {
        @Override
        public void run() {
            //logger.info("进入ConsumerRunnable的run方法");
            productService.consumeByItem(1000, 1);
            logger.info(Thread.currentThread().getName()+"消费："+1);
        }
    }

    @Test
    public void ProConTest() {
        for (int i = 0; i < 30; i++) {
            new Thread(new ProductServiceTest.ProducerRunnable()).start();
            new Thread(new ProductServiceTest.ConsumerRunnable()).start();
        }
    }
}