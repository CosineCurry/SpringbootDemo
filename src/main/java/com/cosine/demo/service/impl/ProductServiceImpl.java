package com.cosine.demo.service.impl;

import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductDTO;
import com.cosine.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName ProductServiceImpl
 * @Description 商品Service实现类
 * @Author cosine
 * @Date 2021/5/28 11:11
 * @Version 1.0
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private StoreDao storeDao;

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    /** rollbackFor = Exception.class：让checked例外也回滚 */
    @Transactional(rollbackFor = Exception.class)
    public String addProduct(ProductDTO productDTO) {
        /**
         * 将以下两个动作封装为事务：
         * 1. 商品表中增加一条记录
         * 2. 库存表中增加库存
         */
        Product product = new Product(productDTO.getProductId(), productDTO.getName(), productDTO.getItemId(), productDTO.getCreateFactory(), new Date(), 0, 0);
        int productRes = 0, storeRes = 0;
        try {
            productRes = productDao.insert(product);
            storeRes = storeDao.updateNumber(product.getItemId(), 1);
        } catch (Exception e) {
            logger.error("生产商品失败",e);
            throw e;
        }

        if (productRes == 1 && storeRes == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String consumeProducts(int itemId, int number) {
        /**
         * 将以下两个动作封装为事务：
         * 1. 库存表中减库存（sql语句中有保证，库存要大于等于消费商品数量）
         * 2. 商品表中逻辑删除消费量的商品
         */
        int storeRes = 0, productRes = 0;
        try {
            storeRes = storeDao.updateNumber(itemId, -number);
            //经过库存的保证，商品表中一定存在大于等于这个数量的商品
            productRes = productDao.updateStatus(itemId, number);
        } catch (Exception e) {
            logger.error("消费商品失败",e);
            throw e;
        }

        if (productRes == number && storeRes == 1) {
            return ResResultUtil.SUCCESS;
        } else {
            //抛出非受检异常
            throw new RuntimeException();
        }

    }

}
