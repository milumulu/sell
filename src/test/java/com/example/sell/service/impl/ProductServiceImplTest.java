package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductInfo;
import com.example.sell.enums.ProductStatusEnum;
import com.example.sell.repository.ProductInfoRepository;
import com.example.sell.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    private final String PRODUCT_ID = "123456";

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo result = productService.findOne("123456");
        Assert.assertNotNull(result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productService.findUpAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> result = productService.findAll(request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123459");
        productInfo.setProductName("油条");
        productInfo.setProductPrice(new BigDecimal(5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("一根");
        productInfo.setProductIcon("Http://xxxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(103);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() {
        ProductInfo result = productService.onSale(PRODUCT_ID);
        Assert.assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo result = productService.offSale(PRODUCT_ID);
        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
    }
}