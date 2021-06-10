package com.cosine.demo.controller;

import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Product;
import com.cosine.demo.dto.ProductDTO;
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
 * @Description 生产商品的Controller，专为自己写的前端提供服务
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
     * 生产者首页，商品列表页面
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
     * @param productDTO
     * @return
     */
    @GetMapping("signup")
    public String showSignUpForm(ProductDTO productDTO) {
        return "add-product";
    }

    /**
     * 添加商品逻辑
     * 封装为事务
     * @param productDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("addProduct")
    public String addProduct(@Valid ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        Product product = new Product(productDTO.getProductId(), productDTO.getName(), productDTO.getPrice(), productDTO.getItemId(), productDTO.getCreateFactory(), new Date(), 0, 0);
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
