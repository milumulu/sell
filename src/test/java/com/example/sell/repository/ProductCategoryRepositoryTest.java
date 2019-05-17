package com.example.sell.repository;

import com.example.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void createTest(){
        ProductCategory productCategory = new ProductCategory("测试类目2",103);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

//    @Test
//    public void updateTest(){
//        ProductCategory productCategory = new ProductCategory("测试类目2",103);
//        ProductCategory result = repository.(productCategory);
//        Assert.assertNotNull(result);
//    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(101,102,103);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByAll(){
        List<ProductCategory> result = repository.findAll();
        Assert.assertNotEquals(0, result.size());
    }
}