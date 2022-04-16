package com.example.util;

/**
 * @author : niejun
 * @Description: 字符串首字母大写转化
 * @date Date : 2022年04月16日 17:13
 **/
public class StringUpper {
    public static String doSwitch(String source) {
        if (source == null || source == "") {
            return null;
        }
        String s1 = source.substring(0,1);
        String s2 = source.substring(1);
        return s1.toUpperCase() + s2;
    }
}
