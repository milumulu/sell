package com.example.sell.dataobject;

import com.example.sell.enums.CodeEnum;
import com.example.sell.enums.ProductStatusEnum;
import com.example.sell.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -8155742795683068946L;

    @Id
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

    /** 商品状态 */
    private  Integer productStatus = ProductStatusEnum.UP.getCode();

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
