package com.example.sell.service.impl;

import com.example.sell.dataobject.SellerInfo;
import com.example.sell.repository.SellerInfoRepository;
import com.example.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description:
 * @author: 马建鹏
 * @create: 2019-05-27 10:42
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
