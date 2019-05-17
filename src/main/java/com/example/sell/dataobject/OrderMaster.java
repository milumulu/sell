package com.example.sell.dataobject;

import com.example.sell.enums.OrderStatusEnum;
import com.example.sell.enums.PayStatusEunm;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class OrderMaster {

    @Id
    private String orderId;

    /** 买家名字 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信OpenId */
    private String buyerOpenid;

    /** 订单总额 */
    private BigDecimal orderAmount;

    /** 订单状态，默认0为新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认0为未支付 */
    private Integer payStatus = PayStatusEunm.WAIT.getCode();

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}
