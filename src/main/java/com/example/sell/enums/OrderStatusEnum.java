package com.example.sell.enums;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
public enum OrderStatusEnum implements CodeEnum<Integer> {
    NEW(0, "新订单"),
    FINISHED(1, "已完成"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
