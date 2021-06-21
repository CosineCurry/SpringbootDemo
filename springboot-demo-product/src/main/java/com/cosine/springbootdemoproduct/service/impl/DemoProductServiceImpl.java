package com.cosine.springbootdemoproduct.service.impl;

import com.cosine.springbootdemoproduct.dao.DemoProductDao;
import com.cosine.springbootdemoproduct.entity.Product;
import com.cosine.springbootdemoproduct.entity.ProductDTO;
import com.cosine.springbootdemoproduct.service.DemoProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName DemoProductServiceImpl
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/17 16:06
 * @Version 1.0
 */
@Service(value = "demoProductService")
public class DemoProductServiceImpl implements DemoProductService {

    @Autowired
    private DemoProductDao demoProductDao;

    @Override
    public String addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setCreateFactory(productDTO.getCreateFactory());
        product.setPrice(productDTO.getPrice());
        product.setCreateTime(new Date());
        product.setName(productDTO.getName());
        product.setStatus(0);
        product.setItemId(productDTO.getItemId());
        product.setVersion(0);
        int res = demoProductDao.insert(product);
        if (res == 1) {
            return "成功";
        }
        return "失败";
    }
}
