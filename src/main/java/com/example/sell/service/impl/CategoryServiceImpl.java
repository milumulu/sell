package com.example.sell.service.impl;

import com.example.sell.dataobject.ProductCategory;
import com.example.sell.repository.ProductCategoryRepository;
import com.example.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categorTypeList) {
        return repository.findByCategoryTypeIn(categorTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
