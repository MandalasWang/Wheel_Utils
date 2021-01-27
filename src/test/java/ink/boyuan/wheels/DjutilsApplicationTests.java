package ink.boyuan.wheels;

import ink.boyuan.wheels.easyexcel.listen.BaseDataProcessorAdapter;
import ink.boyuan.wheels.easyexcel.model.ComplexHeadDemo;
import ink.boyuan.wheels.easyexcel.util.EasyExcelReadUtil;
import ink.boyuan.wheels.easyexcel.util.EasyExcelWriteUtil;
import ink.boyuan.wheels.imgutil.util.ImageRemarkUtil;
import ink.boyuan.wheels.imgutil.util.QrCodeUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

class DjutilsApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String property = System.getProperty("user.dir");
        QrCodeUtil.generateQRCodeImge("www.chuyuan.ink",350,350,"D:\\work\\img\\MyBlogAddress.png");
        InputStream inputStream = new FileInputStream("D:\\work\\img\\1.png");
        //ImageRemarkUtil.markImageByText("测试复印无效",inputStream,"D:\\work\\img\\1new.png");

        ImageRemarkUtil.markImageByText("测试复印无效",inputStream,"D:\\work\\img\\1new45.png",-45);
    }

    @Test
    void test() throws FileNotFoundException {
        List<ComplexHeadDemo> list = new CopyOnWriteArrayList();
        list.add( new ComplexHeadDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new ComplexHeadDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new ComplexHeadDemo().setAge(3).setDate("2020-03-21").setName("小花"));
        OutputStream outputStream = new FileOutputStream("D:\\work\\excel\\complex.xlsx");
        EasyExcelWriteUtil.writeExcelIn(outputStream,list,"",ComplexHeadDemo.class);
//        InputStream inputStream = new FileInputStream("D:\\work\\excel\\report.xlsx");
//        //List<Map<T, T>> maps = EasyExcelReadUtil.noModelRead(inputStream);
//
//        List<DataDemo> dataDemos = EasyExcelReadUtil.repeatedReadToAllSheet(inputStream, DataDemo.class);
//        System.out.println(dataDemos);
    }


    @Test
    void testComplexTitle() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\work\\excel\\report.xlsx");
        List<Map<T, T>> maps = EasyExcelReadUtil.noModelRead(inputStream);
        System.out.println(maps);
    }

    @Test
    void testCustomer() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\work\\excel\\report.xlsx");
        BaseDataProcessorAdapter dataProcessor = new dataProcess();
        List<Map<T, T>> maps = EasyExcelReadUtil.customerProcessRead(inputStream,1,dataProcessor);
        System.out.println(maps);
//        dataProcessor.saveData(maps);

    }


    class dataProcess extends BaseDataProcessorAdapter{


        @Override
        public void saveNoModelData(List list) {
            System.out.println("实现适配器 数据加工类");
        }
    }

}
