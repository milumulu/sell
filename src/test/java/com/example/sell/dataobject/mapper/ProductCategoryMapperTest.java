package com.example.sell.dataobject.mapper;

import com.example.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "老婆最爱2");
        map.put("categoryType", "110");
        int result = mapper.insertByMap(map);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory("老婆最爱3", 123);
        int result = mapper.insertByObject(productCategory);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(123);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> result = mapper.findByCategoryName("老婆最爱");
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("老公最爱", 123);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = mapper.findByCategoryType(123);
        productCategory.setCategoryName("老婆最爱");
        int result = mapper.updateByObject(productCategory);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(110);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory result = mapper.selectByCategoryType(103);
        Assert.assertNotNull(result);
    }
}