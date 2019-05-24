package com.example.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description: 商品表单
 * @author: 马建鹏
 * @create: 2019-05-24 16:22
 */
@Data
public class ProductForm {

    private String productId;

    /** 商品名称 */
    private  String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private  Integer productStock;

    /** 商品描述 */
    private  String productDescription;

    /** 商品图标 */
    private  String productIcon;

    /** 商品类别 */
    private  Integer categoryType;
}
