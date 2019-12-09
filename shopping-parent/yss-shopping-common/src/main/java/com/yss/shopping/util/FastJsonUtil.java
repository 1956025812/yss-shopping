package com.yss.shopping.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastJson工具类
 */
public class FastJsonUtil {

    /**
     * json字符串转map集合
     *
     * @param jsonStr JSON字符串
     * @return MAP集合
     */
    public static HashMap<String, String> json2Map(String jsonStr) {
        return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
    }


    /**
     * map转json字符串
     *
     * @param map MAP集合
     * @return JSON字符串
     */
    public static String map2Json(Map<String, String> map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }


    /**
     * json字符串转换成对象
     *
     * @param jsonString JSON字符串
     * @param cls        CLASS类
     * @return CLASS对象
     */
    public static <T> T json2Bean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 对象转换成json字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }


    /**
     * json字符串转换成List集合
     * (需要实体类)
     *
     * @param jsonString JSON字符串
     * @return 对象LIST集合
     */
    public static <T> List<T> json2List(String jsonString, Class cls) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * json字符串转换成ArrayList集合
     * (需要实体类)
     *
     * @param jsonString JSON字符串
     * @return 对象ArrayList集合
     */
    public static <T> ArrayList<T> json2ArrayList(String jsonString, Class cls) {
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * List集合转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String list2Json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }


    /**
     * json转List
     * (不需要实体类)
     *
     * @param jsonStr JSON字符串
     * @return JSONArray
     */
    public static JSONArray json2List(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }


}