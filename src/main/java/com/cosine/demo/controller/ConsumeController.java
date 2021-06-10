package com.cosine.demo.controller;

import com.cosine.demo.common.CommonUtil;
import com.cosine.demo.dao.OrderDao;
import com.cosine.demo.dao.OrderItemDao;
import com.cosine.demo.dao.ProductDao;
import com.cosine.demo.dao.StoreDao;
import com.cosine.demo.domain.Order;
import com.cosine.demo.domain.OrderItem;
import com.cosine.demo.dto.ConsumeFrontDTO;
import com.cosine.demo.dto.ProductConsumeDTO;
import com.cosine.demo.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ConsumeController
 * @Description 消费商品的controller，专为自己写的前端提供服务
 * @Author cosine
 * @Date 2021/6/7 15:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/consume/")
public class ConsumeController {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private StoreDao storeDao;

    static final Logger logger = LoggerFactory.getLogger(ConsumeController.class);

    /**
     * 消费者首页
     * @param model
     * @return
     */
    @GetMapping("consumeHome")
    public String produceForm(Model model) {
        model.addAttribute("products", productDao.findAllByPage());
        return "consumeMain";
    }


    /**
     * 计算价格接口
     * @param consumeFrontDTO
     * @return
     */
    @PostMapping("calculatePrice")
    @ResponseBody
    public String calculatePrice(@RequestBody ConsumeFrontDTO consumeFrontDTO) {
        logger.info(String.valueOf(consumeFrontDTO));
        String[] nums = consumeFrontDTO.getProductIds();
        ArrayList<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new BigInteger(nums[i]));
        }
        List<ProductDTO> productDTOList = productDao.findProductsByIds(list);
        if (list.size() == productDTOList.size()) {
            //类型转换
            ProductConsumeDTO productConsumeDTO = new ProductConsumeDTO(consumeFrontDTO.getUserId(), consumeFrontDTO.getDiscountType(), productDTOList);
            //计算总价
            BigDecimal price = CommonUtil.calculateTotalPrice(productConsumeDTO.getProductDTOS());
            //计算优惠后的价格
            Integer discountType = productConsumeDTO.getDiscountType();
            BigDecimal totalPrice = new CommonUtil().calculatePrice(discountType, new Double(10), price);
            logger.info("总价为："+totalPrice);
            return String.valueOf(totalPrice);
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * 消费相关事务
     * 加了ResponseBody注解后返回的是字符串
     * @param consumeFrontDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("consumeSuccess")
    @ResponseBody
    public String consumeOrder(@RequestBody ConsumeFrontDTO consumeFrontDTO) {

        String[] nums = consumeFrontDTO.getProductIds();
        ArrayList<BigInteger> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new BigInteger(nums[i]));
        }
        List<ProductDTO> productDTOList = productDao.findProductsByIds(list);
        if (list.size() == productDTOList.size()) {
            //类型转换
            ProductConsumeDTO productConsumeDTO = new ProductConsumeDTO(consumeFrontDTO.getUserId(), consumeFrontDTO.getDiscountType(), productDTOList);
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
            for (int i = 0; i < list.size(); i++) {
                orderItemDao.insert(new OrderItem(new BigInteger(String.valueOf(0)),orderId, productConsumeDTO.getProductDTOS().get(i).getProductId(), productConsumeDTO.getProductDTOS().get(i).getPrice()));
            }
            //减库存（商品表和库存表）
            for (int i = 0; i < list.size(); i++) {
                storeDao.updateNumber(productConsumeDTO.getProductDTOS().get(i).getItemId(), -1);
            }
            productDao.updateStatusById(list);
            return "success";

        } else {
            throw new RuntimeException();
        }
    }


    /**
     * 跳转到购买成功页
     * @return
     */
    @GetMapping("purchaseSuccess")
    public String purchaseSuccess() {
        return "purchase-success";
    }


}
