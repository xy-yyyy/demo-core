package com.demo.pay.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 14:44 2020/8/27
 */
public class GsonUtils {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private static Gson gsonDate = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static String toJsonDate(Object value) {
        return gsonDate.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonParseException {
        return (T) gson.fromJson(json, typeOfT);
    }


}

