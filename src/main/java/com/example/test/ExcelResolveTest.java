package com.example.test;

import com.alibaba.excel.EasyExcelFactory;

/**
 * @ClassName ExcelResolveTest
 * @Author niejun
 * @Date 2022/4/15
 * @Description:
 * @Version 1.0
 **/
public class ExcelResolveTest {
    public static void main(String[] args) {
        String path = "D:\\1.xls";

        EasyExcelFactory.read(path, Object.class, null).sheet().headRowNumber(2).doReadSync();
    }
}
