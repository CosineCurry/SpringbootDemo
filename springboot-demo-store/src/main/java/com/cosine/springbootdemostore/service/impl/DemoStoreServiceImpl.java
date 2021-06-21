package com.cosine.springbootdemostore.service.impl;

import com.cosine.springbootdemostore.dao.DemoStoreDao;
import com.cosine.springbootdemostore.service.DemoStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoStoreServiceImpl
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/18 13:49
 * @Version 1.0
 */
@Service(value = "demoStoreService")
public class DemoStoreServiceImpl implements DemoStoreService {

    @Autowired
    private DemoStoreDao demoStoreDao;

    @Override
    public String plusOne(int itemId) {
        int res = demoStoreDao.plusOne(itemId);
        if (res == 1) {
            return "成功";
        }
        return "失败";
    }
}
