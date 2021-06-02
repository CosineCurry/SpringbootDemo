package com.cosine.demo.controller;

import com.cosine.demo.common.ResResult;
import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Store;
import com.cosine.demo.service.StoreService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
