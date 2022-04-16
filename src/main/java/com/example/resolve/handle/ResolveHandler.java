package com.example.resolve.handle;

import com.example.resolve.listener.Listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : niejun
 * @Description: 处理器抽象类
 * @date Date : 2022年04月16日 14:59
 **/
public abstract class ResolveHandler<T> {

    public List<Object> result = new ArrayList<>(10);

    public List<Listener> listenerList = new ArrayList<Listener>(10);

    public String filePath;

    public Class clazz;

    public Map<Integer, Field> fieldSetMap = new TreeMap<Integer, Field>();

    public abstract List<Object> doHandler() throws Exception;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void addListener(Listener listener) {
        listenerList.add(listener);
    }

    public void addListener(List<Listener> list) {
        this.listenerList.addAll(list);
    }

    public void registClass(Class clazz) {
        this.clazz = clazz;
    }

    public List<Object> getResult() {
        return result;
    }
}
