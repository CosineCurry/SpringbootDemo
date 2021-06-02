package com.cosine.demo.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    private StoreService storeService;
    static final Logger logger = LoggerFactory.getLogger(StoreServiceTest.class);

    /**
     * 生产者
     */
    class ProducerRunnable implements Runnable {
        @Override
        public void run() {
            //logger.info("进入ProducerRunnable的run方法");
            storeService.updateCount(1000, 1);
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
            storeService.updateCount(1000, -1);
            logger.info(Thread.currentThread().getName()+"消费："+1);
        }
    }

    @Test
    public void updateCountTest() {
        for (int i = 0; i < 30; i++) {
            new Thread(new ProducerRunnable()).start();
            new Thread(new ConsumerRunnable()).start();
        }
    }

}