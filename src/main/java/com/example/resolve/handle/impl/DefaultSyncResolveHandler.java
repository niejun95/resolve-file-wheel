package com.example.resolve.handle.impl;

import com.example.resolve.handle.SyncResolveHandler;
import com.example.util.ResolveUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : niejun
 * @Description: 默认同步处理器
 * @date Date : 2022年04月16日 19:03
 **/
public class DefaultSyncResolveHandler<T> extends SyncResolveHandler<T> {

    public List<Object> list = new ArrayList<>(10);

    @Override
    public List<Object> doSyncHandler() throws InvocationTargetException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        List<Object> result = new ArrayList<>(10);
        String[] arr = null;
        String contentLine = null;
        Map<Integer, Field> fieldSetMap= ResolveUtils.getAnnotationField(clazz);

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while ((contentLine = br.readLine()) != null) {
            ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
            T obj = type.newInstance();
            arr = contentLine.split("\\|");
            for (int index = 0; index < arr.length; index++) {
                Field field = fieldSetMap.get(index);
                String fieldSetName = ResolveUtils.spliceSetMethod(field.getName());
                Method fieldSetMet = clazz.getMethod(fieldSetName, field.getType());
                fieldSetMet.invoke(obj, ResolveUtils.getFieldTypeValue(field, arr[index]));
            }
            list.add(obj);

        }
        return list;
    }
}
