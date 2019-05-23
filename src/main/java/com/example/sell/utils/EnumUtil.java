package com.example.sell.utils;

import com.example.sell.enums.CodeEnum;

/**
 * @program: sell
 * @description: 枚举工具类
 * @author: 马建鹏
 * @create: 2019-05-23 16:04
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
