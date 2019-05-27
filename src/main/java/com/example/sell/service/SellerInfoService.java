package com.example.sell.service;

import com.example.sell.dataobject.SellerInfo;
import org.springframework.stereotype.Service;

/**
 * @program: sell
 * @description: 卖家serivce
 * @author: 马建鹏
 * @create: 2019-05-27 10:40
 */
@Service
public interface SellerInfoService {

    SellerInfo findByOpenid(String openid);
}
