package com.example.resolve.config;

import com.example.resolve.handle.AsyncResolveHandler;
import com.example.resolve.handle.ResolveHandler;
import com.example.resolve.handle.SyncResolveHandler;
import com.example.resolve.handle.impl.DefaultAsyncResolveHandler;
import com.example.resolve.handle.impl.DefaultSyncResolveHandler;
import com.example.resolve.listener.Listener;
import com.example.resolve.listener.impl.DefaultListener;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * @ClassName ResolveBuilder
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class ResolveBuilder {

    private java.util.logging.Logger logger = Logger.getLogger(this.getClass().getName());

    public List<Listener> listenerList = new ArrayList<Listener>(10);

    public String filePath;

    public Class clazz;

    public ResolveHandler<T> handler;

    public ResolveBuilder() {
    }
    /**
     * 异步读取
     * @return
     * @throws Exception
     */
    public void doAyncRead() throws Exception {
        handler = new DefaultAsyncResolveHandler<>();
        handler.setFilePath(filePath);
        handler.registClass(clazz);
        handler.addListener(listenerList);
        handler.doHandler();
    }

    /**
     * 同步读取
     * @return
     * @throws Exception
     */
    public List<Object> doSyncRead() throws Exception {
        handler = new DefaultSyncResolveHandler<>();
        handler.setFilePath(filePath);
        handler.registClass(clazz);
        handler.addListener(listenerList);
        handler.doHandler();
        return handler.getResult();
    }


    public void file(String filePath) {
        this.filePath = filePath;
    }

    public void head(Class clazz) {
        this.clazz = clazz;
    }

    public void addListener(Listener listener) {
        if (listener == null) {
            listenerList.add(new DefaultListener());
        } else {
            listenerList.add(listener);
        }
    }

    public List<Object> getResult() {
        return handler.getResult();
    }
}
