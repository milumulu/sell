package com.example.sell.service;

import com.example.sell.dto.OrderDTO;

public interface PushMessageService {

    void orderStatus(OrderDTO orderDTO);
}
