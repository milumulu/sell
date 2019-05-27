package com.example.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: sell
 * @description: 卖家信息表
 * @author: 马建鹏
 * @create: 2019-05-27 10:16
 */
@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
