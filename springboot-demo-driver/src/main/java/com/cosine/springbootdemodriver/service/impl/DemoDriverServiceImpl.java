package com.cosine.springbootdemodriver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.cosine.springbootdemodriver.entity.ProductDTO;
import com.cosine.springbootdemodriver.service.DemoDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName DemoDriverServiceImpl
 * @Description TODO
 * @Author cosine
 * @Date 2021/6/18 15:20
 * @Version 1.0
 */
@Service(value = "demoDriverService")
public class DemoDriverServiceImpl implements DemoDriverService {

    @Autowired
    private RestTemplate restTemplate;

    /** 用于测试nacos的服务发现 */
    @NacosInjected
    private NamingService namingService;

    private final Logger logger = LoggerFactory.getLogger(DemoDriverServiceImpl.class);
    private static final String productServiceNAME = "product-provider";
    private static final String storeServiceNAME = "store-provider";
    private static String product_URL_PREFIX = "";
    private static String store_URL_PREFIX = "";

    /**
     * 通过服务名获取IP+端口
     */
    public void getURLPrefix() throws NacosException {

        Instance productInstance = namingService.selectOneHealthyInstance(productServiceNAME);
        Instance storeInstance = namingService.selectOneHealthyInstance(storeServiceNAME);
        product_URL_PREFIX = "http://" + productInstance.getIp() + ":" + productInstance.getPort();
        store_URL_PREFIX = "http://" + storeInstance.getIp() + ":" + storeInstance.getPort();
        logger.info(product_URL_PREFIX + " " + store_URL_PREFIX);
    }

    @Override
    public String produce(ProductDTO productDTO) {

        logger.info("生产商品"+productDTO.toString());

        try {
            getURLPrefix();
        } catch (NacosException e) {
            e.printStackTrace();
        }


        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept",MediaType.APPLICATION_JSON_VALUE);
        //body
        JSONObject param = new JSONObject();
        param.put("createFactory", productDTO.getCreateFactory());
        param.put("itemId", productDTO.getItemId());
        param.put("name", productDTO.getName());
        param.put("price", productDTO.getPrice());
        param.put("productId", productDTO.getProductId());
        //HttpEntity
        HttpEntity formEntity = new HttpEntity(param, requestHeaders);
        //加库存
        String storeRes = this.restTemplate.getForObject(store_URL_PREFIX + "/demo-store/plusOne/"+productDTO.getItemId(), String.class);
        //增加商品
        String productRes = this.restTemplate.postForObject(product_URL_PREFIX + "/demo-product/produce", formEntity, String.class);
        logger.info("库存返回结果："+storeRes);
        logger.info("商品返回结果："+productRes);
        if (productRes.equals("成功") && storeRes.equals("成功")) {
            return "成功";
        } else {
            // 人工进行事务回滚，发送两个http请求，一个减库存，一个减商品。
            return "失败";
        }
    }
}
