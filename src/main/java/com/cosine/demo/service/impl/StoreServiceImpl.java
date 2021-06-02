package com.cosine.demo.service.impl;

import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Store;
import com.cosine.demo.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName StoreServiceImpl
 * @Description 库存service实现类
 * @Author cosine
 * @Date 2021/6/2 10:31
 * @Version 1.0
 */
@Service(value = "storeService")
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    private final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

    @Override
    public String addStore(Store store) {
        int res = 0;
        try {
            res = storeDao.insert(store);
        } catch (Exception e) {
            logger.error("插入数据失败",e);
        }
        if (res == 1) {
            return ResResultUtil.SUCCESS;
        }
        return ResResultUtil.FAIL;
    }

    @Override
    public String updateCount(int productId, int count) {
        //先去查这个商品id的库存
        int num = 0;
        try {
            num = storeDao.getNumber(productId);
            logger.info("库存为："+num);
        } catch (Exception e) {
            logger.error(""+e);
            return ResResultUtil.FAIL;
        }
        int total = num + count;
        //如果购买的商品数量大于库存
        if (total < 0) {
            return ResResultUtil.FAIL;
        }
        try {
            storeDao.updateNumber(productId, total);
        } catch (Exception e) {
            logger.error("更新库存失败"+e);
            return ResResultUtil.FAIL;
        }
        return ResResultUtil.SUCCESS;
    }
}
