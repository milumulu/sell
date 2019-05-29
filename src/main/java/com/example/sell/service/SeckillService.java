package com.example.sell.service;

/**
 * @author Lixh
 * @Date: 2018/5/19 10:18
 * @Description:
 */
public interface SeckillService {

    String querySeckillProductInfo(String productId) throws Exception;
    void orderProductMockDiffUser(String productId);
}
