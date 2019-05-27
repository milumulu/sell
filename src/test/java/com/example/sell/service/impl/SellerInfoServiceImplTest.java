package com.example.sell.service.impl;

import com.example.sell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    private static final String OPENID = "13213213";

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @Test
    public void findByOpenid() {
        SellerInfo result = sellerInfoService.findByOpenid(OPENID);
        Assert.assertNotNull(result);
    }
}