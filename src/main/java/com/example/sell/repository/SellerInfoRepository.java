package com.example.sell.repository;

import com.example.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: sell
 * @description:
 * @author: 马建鹏
 * @create: 2019-05-27 10:19
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
