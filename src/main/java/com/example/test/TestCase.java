package com.example.test;

import com.example.entities.Person;
import com.example.resolve.base.ResolveFactory;
import com.example.resolve.process.ResolveBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
        String filePath = "D:\\1.txt";
        try {
            ResolveBuilder builder = ResolveFactory.read(filePath, Person.class);
            List result = builder.doRead(null);
            for (Object obj : result) {
                System.out.println(obj);
            }
        } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
