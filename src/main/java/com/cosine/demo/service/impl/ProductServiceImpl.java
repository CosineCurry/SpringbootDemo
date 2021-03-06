package com.cosine.demo.service.impl;

import com.cosine.demo.common.CommonUtil;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.OrderDao;
import com.cosine.demo.dao.OrderItemDao;
import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Order;
import com.cosine.demo.domain.OrderItem;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductConsumeDTO;
import com.cosine.demo.dto.ProductDTO;
import com.cosine.demo.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

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
        Product product = new Product(productDTO.getProductId(), productDTO.getName(), productDTO.getPrice(),productDTO.getItemId(), productDTO.getCreateFactory(), new Date(), 0, 0);
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
    public PageInfo<ProductDTO> findAllProductsByPage(int page, int offset) {
        //这句是核心
        PageHelper.startPage(page, offset);
        List<ProductDTO> all = productDao.findAllByPage();
        return new PageInfo<>(all);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String consumeByItem(int itemId, int number) {
        /**
         * 将以下两个动作封装为事务：
         * 1. 先查库存，如果满足条件就执行2和3，不满足条件就抛异常
         * 2. 库存表中减库存
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String consumeProducts(ProductConsumeDTO productConsumeDTO) {
        /**
         * 拆分业务，有些方法不用放到事务中，比如1和2，提高性能。
         * 将3 4 5封装为事务：
         * 1. 先查库存，如果商品还存在于库存（没被锁单）就继续执行，不满足条件就抛异常
         * 2. 计算总价，计算优惠后的价格
         * 3. 生成订单，加入数据库（订单表和订单Item表，一对多）
         * 4. 库存表中减库存
         * 5. 商品表中逻辑删除消费量的商品
         *
         */

        ArrayList<BigInteger> arrayList = new ArrayList<>();
        for (int i = 0; i < productConsumeDTO.getProductDTOS().size(); i++) {
            arrayList.add(productConsumeDTO.getProductDTOS().get(i).getProductId());
        }
        //查库存
        int res = productDao.searchProduct(arrayList);
        logger.info("查询得到总共"+res+"条商品数据");
        if (res == productConsumeDTO.getProductDTOS().size()) {
            //计算总价
            BigDecimal price = CommonUtil.calculateTotalPrice(productConsumeDTO.getProductDTOS());
            //计算优惠后的价格
            Integer discountType = productConsumeDTO.getDiscountType();
            BigDecimal totalPrice = new CommonUtil().calculatePrice(discountType, new Double(10), price);
            //生成订单主表
            BigInteger orderId = new BigInteger(String.valueOf(System.currentTimeMillis()));
            Date time = new Date();
            BigInteger userId = productConsumeDTO.getUserId();
            orderDao.insert(new Order(orderId, totalPrice, time, time, 0, 0, userId, discountType));
            //生成订单项
            for (int i = 0; i < res; i++) {
                orderItemDao.insert(new OrderItem(new BigInteger(String.valueOf(0)),orderId, productConsumeDTO.getProductDTOS().get(i).getProductId(), productConsumeDTO.getProductDTOS().get(i).getPrice()));
            }
            //减库存（商品表和库存表）
            for (int i = 0; i < res; i++) {
                storeDao.updateNumber(productConsumeDTO.getProductDTOS().get(i).getItemId(), -1);
            }
            productDao.updateStatusById(arrayList);
            return ResResultUtil.SUCCESS;
        } else {
            throw new RuntimeException();
        }

    }

}
