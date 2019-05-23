package com.example.sell.utils;

/**
 * @program: sell
 * @description: MathUtil
 * @author: 马建鹏
 * @create: 2019-05-23 11:27
 */
public class MathUtil {

    private static final Double Money_Range = 0.01;

    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if(result < Money_Range) {
            return true;
        }
        return false;
    }
}
