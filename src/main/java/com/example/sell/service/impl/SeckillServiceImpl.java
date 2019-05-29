package com.example.sell.service.impl;

import com.example.sell.enums.ResultEnum;
import com.example.sell.exception.SellException;
import com.example.sell.service.RedisLock;
import com.example.sell.service.SeckillService;
import com.example.sell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Lixh
 * @Date: 2018/5/19 10:19
 * @Description:
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    RedisLock redisLock;

    private static final int TIME_OUT = 1 * 1000;

    /**
     * 国庆活动，皮蛋粥特价， 限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();

        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMap(String productId) {
        return "国庆活动，皮蛋瘦肉粥，限量份"
                + products.get(productId)
                + " 还剩 " + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目："
                + orders.size()
                + " 人";
    }

    @Override
    public synchronized void orderProductMockDiffUser(String productId) {
        //1.查询改商品库存，为0则活动结束。
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        }else {
            //2.下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(), productId);
            //3.减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InternalError e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
    }

    @Override
    public String querySeckillProductInfo(String productId) {
        return this.queryMap(productId);
    }
}
