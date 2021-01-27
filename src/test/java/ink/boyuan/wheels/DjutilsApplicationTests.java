package ink.boyuan.wheels;

import ink.boyuan.wheels.easyexcel.listen.BaseDataProcessorAdapter;
import ink.boyuan.wheels.easyexcel.model.ComplexHeadDemo;
import ink.boyuan.wheels.easyexcel.util.EasyExcelReadUtil;
import ink.boyuan.wheels.easyexcel.util.EasyExcelWriteUtil;
import ink.boyuan.wheels.imgutil.util.ImageRemarkUtil;
import ink.boyuan.wheels.imgutil.util.QrCodeUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

class DjutilsApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String property = System.getProperty("user.dir");
        QrCodeUtil.generateQRCodeImge("www.chuyuan.ink",350,350,"D:\\work\\img\\MyBlogAddress.png");
        InputStream inputStream = new FileInputStream("D:\\work\\img\\1.png");
        ImageRemarkUtil.setRemarkData(0.5f,150,150,new Font("宋体", Font.BOLD, 77),Color.LIGHT_GRAY);
        ImageRemarkUtil.markImageByText("测试复印无效",inputStream,"D:\\work\\img\\1new.png");

        ImageRemarkUtil.markImageByText("测试复印无效",inputStream,"D:\\work\\img\\1new45.png",-45);
    }

    @Test
    void test() throws FileNotFoundException {
        List<ComplexHeadDemo> list = new CopyOnWriteArrayList();
        list.add( new ComplexHeadDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new ComplexHeadDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new ComplexHeadDemo().setAge(3).setDate("2020-03-21").setName("小花"));
        OutputStream outputStream = new FileOutputStream("D:\\work\\excel\\repeat2.xlsx");
        EasyExcelWriteUtil.repeatedWrite(outputStream,list,"",ComplexHeadDemo.class,2);
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

    @Test
    void testdanumic() throws Exception {
        OutputStream inputStream = new FileOutputStream("D:\\work\\excel\\dynamic.xlsx");
        EasyExcelWriteUtil.dynamicNoModelWrite(inputStream,head(),dataList(),"","");
    }
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" );
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" );
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" );
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }

    class dataProcess extends BaseDataProcessorAdapter{


        @Override
        public void saveNoModelData(List list) {
            System.out.println("实现适配器 数据加工类");
        }
    }

}
