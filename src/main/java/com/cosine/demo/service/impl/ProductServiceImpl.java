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
         * 将以下三个动作封装为事务：
         * 1. 先查库存，如果满足条件就执行2和3，不满足条件就抛异常
         * 2. 库存表中增加库存
         * 3. 商品表中增加一条记录
         */
        Product product = new Product(productDTO.getProductId(), productDTO.getName(), productDTO.getItemId(), productDTO.getCreateFactory(), new Date(), 0, 0);
        int store= storeDao.getNumber(product.getItemId());
        int maxCount = storeDao.getMaxCount(product.getItemId());
        if (store < maxCount) {
            storeDao.updateNumber(product.getItemId(), 1);
            productDao.insert(product);
        } else {
            //抛出非受检异常
            throw new RuntimeException();
        }
        return ResResultUtil.SUCCESS;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String consumeProducts(int itemId, int number) {
        /**
         * 将以下两个动作封装为事务：
         * 1. 先查库存，如果满足条件就执行2和3，不满足条件就抛异常
         * 2. 库存表中减库存（sql语句中有保证，库存要大于等于消费商品数量）
         * 3. 商品表中逻辑删除消费量的商品
         */
        int store= storeDao.getNumber(itemId);
        if (number <= store) {
            storeDao.updateNumber(itemId, -number);
            productDao.updateStatus(itemId, number);
        } else {
            //抛出非受检异常
            throw new RuntimeException();
        }
        return ResResultUtil.SUCCESS;
    }

}
