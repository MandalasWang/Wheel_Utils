package ink.boyuan.wheels.easyexcel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wyy
 * @version 1.0
 * @Classname ComplexHeadDemo
 * @date 2020/11/30 17:24
 * @description
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(value = 20)
@ContentRowHeight(value = 14)
public class ComplexHeadDemo {

    @ExcelProperty(value = {"主标题","年龄"},index = 0)
    private int age;

    @ExcelProperty(value = {"主标题","名字"},index = 1)
    private String name;


    @ExcelProperty(value = {"主标题","出生日期"},index = 2)
    private String date;


}
