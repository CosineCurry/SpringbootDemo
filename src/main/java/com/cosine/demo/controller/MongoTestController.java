package com.cosine.demo.controller;

import com.cosine.demo.common.ResResultUtil;
import com.cosine.demo.domain.Product;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MongoTestController
 * @Description Mongo DB 测试
 * @Author cosine
 * @Date 2021/6/16 15:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/mangodb/")
public class MongoTestController {

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("insert")
    public String insert() {
        Product product = new Product();
        product.setProductId(new BigInteger(String.valueOf(100)));
        product.setCreateTime(new Date());
        product.setName("coco");
        product.setPrice(new BigDecimal(100.0));
        product.setItemId(100);
        product.setCreateFactory("Lifang");
        product.setStatus(0);
        product.setVersion(0);
        product = mongoTemplate.insert(product);
        if (product != null) {
            return ResResultUtil.SUCCESS;
        } else {
            return ResResultUtil.FAIL;
        }
    }
    @GetMapping("query")
    public String query() {
        Query query = Query.query(Criteria.where("name").is("coco").and("status").is(0));
        List<Product> passengers = mongoTemplate.find(query, Product.class);
        return passengers.size() + "";
    }


    @GetMapping("update")
    public String update() {
        Query query = Query.query(Criteria.where("name").is("coco"));
        Update update = new Update();
        update.set("name", "cos");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Product.class);
        return ResResultUtil.SUCCESS;
    }

    @GetMapping("remove")
    public String remove() {
        Query query = Query.query(Criteria.where("name").is("coco"));
        DeleteResult remove = mongoTemplate.remove(query, Product.class);
        return ResResultUtil.SUCCESS;
    }
}
