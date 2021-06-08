package com.cosine.demo.controller;

import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

/**
 * @ClassName ProduceController
 * @Description 生产商品的Controller
 * @Author cosine
 * @Date 2021/6/7 15:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/produce/")
public class ProduceController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private StoreDao storeDao;

    /**
     * 生产者首页
     * @param model
     * @return
     */
    @RequestMapping("produceHome")
    public String produceForm(Model model) {
        model.addAttribute("products", productDao.findAllByPage());
        return "produceMain";
    }

    /**
     * 添加商品页面
     * 封装为事务
     * @param productVO
     * @return
     */
    @GetMapping("signup")
    public String showSignUpForm(ProductVO productVO) {
        return "add-product";
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("addProduct")
    public String addProduct(@Valid ProductVO productVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        Product product = new Product(productVO.getProductId(), productVO.getName(), productVO.getPrice(), productVO.getProductItemId(), productVO.getCreateFactory(), new Date(), 0, 0);
        int store= storeDao.getNumber(product.getItemId());
        int maxCount = storeDao.getMaxCount(product.getItemId());
        if (store < maxCount) {
            storeDao.updateNumber(product.getItemId(), 1);
            productDao.insert(product);
        } else {
            //抛出非受检异常
            throw new RuntimeException();
        }
        return "redirect:produceHome";
    }


}
