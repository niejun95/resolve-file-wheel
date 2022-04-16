package com.example.resolve.handle;

import com.example.resolve.listener.Listener;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : niejun
 * @Description: 处理器抽象类
 * @date Date : 2022年04月16日 14:59
 **/
public abstract class ResolveHandler<T> {

    public List<Listener> list = new ArrayList<Listener>(10);

    public String filePath;

    public Class clazz;


    public abstract List<Object> doSyncHandler() throws InvocationTargetException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    public abstract void doAsyncHandler();


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void addListener(Listener listener) {
        list.add(listener);
    }

    public void addListener(List<Listener> list) {
        this.list.addAll(list);
    }

    public void registClass(Class clazz) {
        this.clazz = clazz;
    }
}
