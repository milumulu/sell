package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        //Assert.assertEquals(1, productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> result = categoryService.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categoryTypeList = Arrays.asList(101,102);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("老婆最爱", 104);
        ProductCategory result = categoryService.save(productCategory);
    }
}