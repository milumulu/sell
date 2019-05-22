package com.example.sell.service;

import com.example.sell.dto.OrderDTO;

/**
 * @program: sell
 * @description: 支付接口
 * @author: 马建鹏
 * @create: 2019-05-22 15:27
 */
public interface PayService {

    void create(OrderDTO orderDTO);
}
