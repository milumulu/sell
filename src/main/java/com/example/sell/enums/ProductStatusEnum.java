package com.example.sell.enums;

import lombok.Getter;

//商品状态
@Getter
public enum ProductStatusEnum implements CodeEnum<Integer> {
    UP(0, "正常"),
    DOWN(1, "下架")
    ;

    private Integer code;

    private  String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
