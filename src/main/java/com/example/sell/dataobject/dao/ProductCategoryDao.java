package com.example.sell.dataobject.dao;

import com.example.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: 马建鹏
 * @create: 2019-05-28 15:50
 */
public class ProductCategoryDao {

    @Autowired
    ProductCategoryMapper mapper;

    public int insertByMap(Map<String, Object> map) {
        return mapper.insertByMap(map);
    }
}
