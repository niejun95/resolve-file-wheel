package com.example.resolve.process;

import com.example.resolve.handle.ResolveHandler;
import com.example.resolve.handle.impl.DefaultResolveHandler;
import com.example.resolve.listener.DefaultListener;
import com.example.resolve.listener.Listener;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
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

    private ResolveHandler<T> handler;

    private String filePath;

    private Class clazz;

    private Integer headRowNum = 0;

    private Listener listener;

    public ResolveBuilder() {
    }

    /**
     * 设置读取文件路径
     * @param filePath
     */
    public void file(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 设置文件读取数据的映射实体类
     * @param clazz
     */
    public void head(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 设置文件头行数
     * @param headRowNum
     */
    public void headRow(Integer headRowNum) {
        this.headRowNum = headRowNum;
    }

    /**
     * 异步读取
     * @return
     */
    public List<Object> doRead(ResolveHandler inhandler) throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException, NoSuchMethodException {
        // 调用handler进行处理 并传入listener
        if (inhandler == null) {
            addHandler(new DefaultResolveHandler());
        } else {
            addHandler(inhandler);
        }
        handler.setFilePath(filePath);
        handler.addListener(new DefaultListener());
        handler.registClass(clazz);
        return handler.doSyncHandler();
    }

    /**
     * 设置文件内容解析监听器
     */
    public void addListener(Listener listener) {
        if (listener == null) {
            this.listener = new DefaultListener();
        }
        this.listener = listener;
    }

    public void addListener(List<Listener> list) {
        handler.addListener(list);
    }

    /**
     * 添加处理器
     */
    public void addHandler(ResolveHandler<T> handler) {
        this.handler = handler;
    }

}
