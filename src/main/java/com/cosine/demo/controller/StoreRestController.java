package com.cosine.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Store;
import com.cosine.demo.service.StoreService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName StoreRestController
 * @Description 库存Controller 实现Restful HTTP服务
 * @Author cosine
 * @Date 2021/6/2 10:28
 * @Version 1.0
 */
@Api(tags = "库存管理")
@RestController
@RequestMapping("/store")
public class StoreRestController {
    @Autowired
    private StoreService storeService;
    static final Logger logger = LoggerFactory.getLogger(StoreRestController.class);

    /** 用于测试nacos的配置管理 */
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    /** 用于测试nacos的服务发现 */
    @NacosInjected
    private NamingService namingService;

    /**
     * 新增一条库存记录
     * @param store
     * @param bindingResult
     * @return
     */
    @PostMapping("/addStoreRecord")
    public ResResult addStoreRecord(@RequestBody Store store, BindingResult bindingResult) {
        logger.info("新增一条库存记录"+store.toString());
        //数据校验
        if (bindingResult.hasErrors()) {
            return ResResultUtil.error(301, bindingResult.getFieldError().getDefaultMessage());
        }
        String str = storeService.addStore(store);
        if (str.equals(ResResultUtil.SUCCESS)) {
            return ResResultUtil.success();
        }
        return ResResultUtil.error(302,"插入数据失败");
    }

    /**
     * 用于测试nacos的配置管理
     * @return
     */
    @GetMapping(value = "/get")
    public boolean get() {
        return useLocalCache;
    }

    /**
     * 用于测试Nacos的服务发现
     * @param serviceName
     * @return
     * @throws NacosException
     */
    @GetMapping(value = "/getServiceName")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

}
