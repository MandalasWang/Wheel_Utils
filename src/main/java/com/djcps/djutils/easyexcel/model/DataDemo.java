package com.djcps.djutils.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;

/**
 * @author wyy
 * @version 1.0
 * @Classname DataDemo
 * @date 2020/11/30 13:34
 * @description
 **/
@Data
@ColumnWidth(value = 20)
@ContentRowHeight(value = 14)
public class DataDemo {


    @ExcelProperty(value = "年龄",index = 0)
    private int age;

    @ExcelProperty(value = "名字",index = 1)
    private String name;


    @ExcelProperty(value = "出生日期",index = 2)
    private String date;


    public DataDemo() {
    }

    public DataDemo(int age, String name, String date) {
        this.age = age;
        this.name = name;
        this.date = date;
    }
}
