package com.example.sell.service;

import com.example.sell.dataobject.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

//类目
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categorTypeList);

    ProductCategory save(ProductCategory productCategory);

    Page<ProductCategory> findAll(Pageable pageable);
}
