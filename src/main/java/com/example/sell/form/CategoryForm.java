package com.example.sell.form;

import lombok.Data;

/**
 * @program: sell
 * @description: 卖家类目表单
 * @author: 马建鹏
 * @create: 2019-05-24 16:55
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目类型 */
    private  Integer categoryType;

}
