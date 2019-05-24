package com.example.sell.controller;

import com.example.sell.dataobject.ProductCategory;
import com.example.sell.exception.SellException;
import com.example.sell.form.CategoryForm;
import com.example.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * @program: sell
 * @description: 卖家类目
 * @author: 马建鹏
 * @create: 2019-05-24 16:41
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ModelAndView list(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page, size);
        Page<ProductCategory> productCategoryPage = categoryService.findAll(request);
        map.put("productCategoryPage", productCategoryPage);
        return new ModelAndView("/category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {

        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("/sell/seller/category/list", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        try {
            ProductCategory productCategory = new ProductCategory();
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
            return new ModelAndView("/sell/seller/category/list", map);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/category/list");
            return new ModelAndView("common/error", map);
        }
    }
}
