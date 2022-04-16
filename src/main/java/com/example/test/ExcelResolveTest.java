package com.example.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;

import java.util.List;

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
        ExcelReaderBuilder readerBuilder = EasyExcelFactory.read(path, Object.class, null);
        ExcelReaderSheetBuilder builder = readerBuilder.sheet();
        ExcelReaderSheetBuilder builder1 = builder.headRowNumber(2);
        List<Object> list = builder1.doReadSync();

        //EasyExcelFactory.read(path, Object.class, null).sheet().headRowNumber(2).doReadSync();
    }
}
