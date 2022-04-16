package com.example.test;

import com.example.annotation.Property;
import com.example.entities.Person;
import com.example.util.StringUpper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author : niejun
 * @Description: TODO
 * @date Date : 2022年04月16日 16:31
 **/
public class ReflTest {

    public static void main(String[] args) throws NoSuchMethodException {
        String str = "ryan|18|male";
        String[] arr = str.split("\\|");
        for (String s : arr) {
            System.out.println(s);
        }

    }



}
