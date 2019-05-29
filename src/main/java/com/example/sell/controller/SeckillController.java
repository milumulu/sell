package com.example.sell.controller;

import com.example.sell.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lixh
 * @Date: 2018/5/19 10:13
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception {
        return seckillService.querySeckillProductInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception {
        log.info("@skill request, productId：" + productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySeckillProductInfo(productId);
    }
}
