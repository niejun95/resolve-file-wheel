package com.example.resolve.handle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author : niejun
 * @Description: 异步处理器抽象类
 * @date Date : 2022年04月16日 19:12
 **/
public abstract class AsyncResolveHandler<T> extends ResolveHandler<T>{

    @Override
    public List<Object> doHandler() throws Exception {
        doAsyncHandler();
        return getResult();
    }

    public abstract void doAsyncHandler() throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}

