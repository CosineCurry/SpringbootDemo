package com.cosine.demo.service.impl;

import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.domain.Product;
import com.cosine.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述：商品Service实现类
 *
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/28 11:11
 * @Version 1.0
 */
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public String addProduct(Product product) {
        int res = 0;
        try {
            res = productDao.insert(product);
        } catch (Exception e) {
            logger.error("插入数据失败",e);
        }
        if (res == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }
}
