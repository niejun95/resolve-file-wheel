package com.example.test;

import com.example.entities.Person;
import com.example.resolve.config.ResolveBuilder;
import com.example.resolve.config.ResolveFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName TestCase
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class TestCase {
    private static java.util.logging.Logger logger = Logger.getLogger(TestCase.class.getName());

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        String filePath = "D:\\1.txt";
        try {
            ResolveBuilder builder = ResolveFactory.read(filePath, Person.class);
            builder.doAyncRead();
            List result = builder.getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
