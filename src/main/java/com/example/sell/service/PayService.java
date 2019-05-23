package com.example.sell.service;

import com.example.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.hibernate.criterion.Order;

/**
 * @program: sell
 * @description: 支付接口
 * @author: 马建鹏
 * @create: 2019-05-22 15:27
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
