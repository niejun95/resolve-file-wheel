package com.example.resolve.handle.impl;

import com.example.annotation.Property;
import com.example.resolve.handle.ResolveHandler;
import com.example.util.StringUpper;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @ClassName DefaultResolveHandler
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class DefaultResolveHandler<T> extends ResolveHandler<T> {

    private java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    private List<Object> list;

    private Map<Integer, Field> fieldSetMap = new TreeMap<Integer, Field>();

    public DefaultResolveHandler() {

    }

    @Override
    public List<Object> doSyncHandler() throws InvocationTargetException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        list = new ArrayList<>(10);
        String[] arr = null;
        String contentLine = null;
        getAnnotationField();

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while ((contentLine = br.readLine()) != null) {
            Object obj = clazz.newInstance();
            arr = contentLine.split("\\|");
            for (int index = 0; index < arr.length; index++) {
                Field field = fieldSetMap.get(index);
                String fieldSetName = spliceSetMethod(field.getName());
                Method fieldSetMet = clazz.getMethod(fieldSetName, field.getType());
                fieldSetMet.invoke(obj, getFieldTypeValue(field, arr[index]));
            }
            list.add(obj);
        }
        return list;

    }

    @Override
    public void doAsyncHandler() {

    }

    public void getAnnotationField() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Property annotation = field.getAnnotation(Property.class);
            if (annotation != null) {
                Integer id = annotation.id();
                fieldSetMap.put(id, field);
            }

        }
    }


    /**
     * 将值转化为属性的类型
     * @param field
     * @param value
     * @return
     */
    public Object getFieldTypeValue(Field field, Object value){
        if(null == field || null == value || "".equals(value)) return null;
        String fieldType = field.getType().getSimpleName();
        if ("String".equals(fieldType)) return String.valueOf(value);
        if ("Date".equals(fieldType)) {
            if (String.valueOf(value).indexOf(':') > 0) return Date.from(LocalDateTime.parse(String.valueOf(value), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
            return Date.from(LocalDate.parse(String.valueOf(value), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if ("Integer".equals(fieldType) || "int".equals(fieldType)) return Integer.parseInt(String.valueOf(value));
        if ("Long".equalsIgnoreCase(fieldType)) return Long.parseLong(String.valueOf(value));
        if ("Float".equalsIgnoreCase(fieldType)) return Float.parseFloat(String.valueOf(value));
        if ("Double".equalsIgnoreCase(fieldType)) return Double.parseDouble(String.valueOf(value));
        if ("Short".equalsIgnoreCase(fieldType)) return Short.parseShort(String.valueOf(value));
        if ("Byte".equalsIgnoreCase(fieldType)) return Byte.parseByte(String.valueOf(value));
        if ("BigDecimal".equalsIgnoreCase(fieldType)) return new BigDecimal(String.valueOf(value));
        if ("Character".equals(fieldType) || "char".equals(fieldType)) return String.valueOf(value).charAt(0);
        if ("Boolean".equalsIgnoreCase(fieldType)) return Boolean.parseBoolean(String.valueOf(value));
        if ("TimeStamp".equalsIgnoreCase(fieldType)) return new Timestamp(Long.valueOf(String.valueOf(value)));
        return value;
    }


    public static String splicePropertyMethod(String fieldName, String method) {
        if (null == fieldName || "".equals(fieldName))
            return null;
        return method + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


    /**
     * 获取属性对应的get方法名
     * @param fieldName
     * @return
     */
    public static String spliceGetMethod(String fieldName) {
        return splicePropertyMethod(fieldName, "get");
    }


    /**
     * 获取属性对应的set方法名
     * @param fieldName
     * @return
     */
    public static String spliceSetMethod(String fieldName) {
        return splicePropertyMethod(fieldName, "set");

    }


}
