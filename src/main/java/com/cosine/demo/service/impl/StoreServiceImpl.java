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
        try {
            storeDao.insert(store);
        } catch (Exception e) {
            logger.error("插入数据失败", e);
        }
        return ResResultUtil.SUCCESS;
    }

    @Override
    public String updateCount(int itemId, int count) {
        try {
            storeDao.updateNumber(itemId, count);
        } catch (Exception e) {
            logger.error("更新库存失败", e);
            return ResResultUtil.FAIL;
        }
        return ResResultUtil.SUCCESS;
    }
}
