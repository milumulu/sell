package com.example.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @program: sell
 * @description: 格式转换
 * @author: 马建鹏
 * @create: 2019-05-22 16:50
 */
public class JsonUtils {

    public static String toJson(Object object) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }
}
