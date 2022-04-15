package com.example.resolve.process;

import java.util.List;

/**
 * @ClassName ResolveBuilder
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class ResolveBuilder {

    private String filePath;

    private Class clazz;

    private Integer headRowNum = 0;

    private ResolveListener resloveListener;

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
    public List<Object> doRead() {

        return null;
    }


    /**
     * 设置文件内容解析监听器
     * @param listener
     */
    public void addListener(ResolveListener listener) {
        this.resloveListener = listener;
    }

}
