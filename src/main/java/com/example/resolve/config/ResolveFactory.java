package com.example.resolve.config;

import com.example.resolve.config.ResolveBuilder;
import com.example.resolve.listener.Listener;

/**
 * @ClassName ResolveFactory
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class ResolveFactory {

    public static ResolveBuilder read() {
        return new ResolveBuilder();
    }

    public ResolveBuilder read(String filePath) {
        return read(filePath, null);
    }

    public static ResolveBuilder read(String filePath, Class clazz) {
        return read(filePath, clazz, null);
    }

    public static ResolveBuilder read(String filePath, Class clazz, Listener listener) {
        ResolveBuilder builder = new ResolveBuilder();
        builder.file(filePath);
        builder.head(clazz);
        builder.addListener(listener);
        return builder;
    }
}
