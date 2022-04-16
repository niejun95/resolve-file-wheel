package com.example.resolve.handle.impl;

import com.example.resolve.handle.AsyncResolveHandler;
import com.example.resolve.handle.ResolveHandler;
import com.example.resolve.listener.Listener;
import com.example.util.ResolveUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author : niejun
 * @Description: 默认异步处理器
 * @date Date : 2022年04月16日 19:03
 **/
public class DefaultAsyncResolveHandler<T> extends AsyncResolveHandler<T> {

    private java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    public void doAsyncHandler() throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        result = new ArrayList<>(10);
        String[] arr = null;
        String contentLine = null;
        Map<Integer, Field> fieldSetMap= ResolveUtils.getAnnotationField(clazz);

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while ((contentLine = br.readLine()) != null) {
            Object obj = clazz.newInstance();
            arr = contentLine.split("\\|");
            for (int index = 0; index < arr.length; index++) {
                Field field = fieldSetMap.get(index);
                String fieldSetName = ResolveUtils.spliceSetMethod(field.getName());
                Method fieldSetMet = clazz.getMethod(fieldSetName, field.getType());
                fieldSetMet.invoke(obj, ResolveUtils.getFieldTypeValue(field, arr[index]));
            }
            result.add(obj);
            for (Listener listener : listenerList) {
                listener.invoke(obj);
            }
        }
    }

    @Override
    public List<Object> doHandler() throws Exception {
        doAsyncHandler();
        return getResult();
    }
}
