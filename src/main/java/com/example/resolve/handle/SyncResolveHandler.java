package com.example.resolve.handle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author : niejun
 * @Description: 同步处理器抽象类
 * @date Date : 2022年04月16日 19:13
 **/
public abstract class SyncResolveHandler<T> extends ResolveHandler<T> {

    public List<Object> doHandler() throws Exception {
        return doSyncHandler();
    }

    public abstract List<Object> doSyncHandler() throws InvocationTargetException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException;


}
