package com.example.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEunm implements CodeEnum<Integer> {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    PAID(3, "已退款"),
    ;

    private Integer code;

    private String message;

    PayStatusEunm(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
