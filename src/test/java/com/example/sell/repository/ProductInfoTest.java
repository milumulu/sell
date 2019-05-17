package com.example.sell.repository;

import com.example.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("小龙虾");
        productInfo.setProductPrice(new BigDecimal(60));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("香辣小龙虾");
        productInfo.setProductIcon("Http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(103);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = repository.findProductInfoByProductStatus(0);
        Assert.assertNotEquals(0, productInfoList.size());
    }



}