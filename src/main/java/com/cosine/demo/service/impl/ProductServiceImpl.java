package com.cosine.demo.service.impl;

import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductPurchaseDTO;
import com.cosine.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

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
        //若创建时间为空，则取当前时间
        if (product.getCreateTime() == null) {
            logger.info("创建时间为空，设置当前时间");
            product.setCreateTime(new Date());
        }
        int res = productDao.insertProduct(product);
        if (res == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }

    @Override
    public String reduceProductNum(ProductPurchaseDTO purchaseDTO) {
        //先去查这个id的库存，如果库存小于购买数，返回失败
        BigInteger productId = new BigInteger(String.valueOf(purchaseDTO.getProductId()));
        int num = 0;
        try {
            num = productDao.getNumber(productId);
            logger.info("库存为："+num);
        } catch (Exception e) {
            logger.error(""+e);
            return ResResultUtil.FAIL;
        }
        if (purchaseDTO.getCount() > num) {
            return ResResultUtil.FAIL;
        }
        try {
            productDao.updateNumber(num - purchaseDTO.getCount(), productId);
        } catch (Exception e) {
            logger.error("",e);
            return ResResultUtil.FAIL;
        }
        return ResResultUtil.SUCCESS;
    }
}
