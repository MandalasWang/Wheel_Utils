package ink.boyuan.wheels.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import ink.boyuan.wheels.base.BaseUtil;
import ink.boyuan.wheels.easyexcel.listen.NoModelDataListener;
import ink.boyuan.wheels.easyexcel.listen.ReadExcelListener;

import ink.boyuan.wheels.easyexcel.listen.base.BaseDataProcessor;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 2.0
 * @Classname EasyExcelReadUtil
 * @date 2020/11/27 14:44
 * @description  主要用于读取Excel 可以指定从第几行开始读取
 **/
public class EasyExcelReadUtil implements BaseUtil {




    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EasyExcelReadUtil.class);


    private volatile static EasyExcelReadUtil instance;

    private EasyExcelReadUtil() {

    }

    public static EasyExcelReadUtil getInstance() {
        //判断是否是空 第一重判断
        if (null == instance) {
            //加锁防止并发
            synchronized (EasyExcelReadUtil.class) {
                if (null == instance) {
                    instance = new EasyExcelReadUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 读取表头数据
     *
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<Map<Integer, String>> headerRead(InputStream inputStream, Class<T> clazz) {
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(inputStream, clazz, dataListener).sheet().doRead();
        return dataListener.getHeadMapList();
    }


    /**
     * 简单的读 不需要对象模板读取出来是map类型
     *
     * @param inputStream 文件流
     * @return 数据源list
     * @author wyy
     */
    public static List<Map<T, T>> noModelRead(InputStream inputStream) {
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        EasyExcel.read(inputStream, noModelDataListener).sheet().doRead();
        return noModelDataListener.getDataList();

    }


    /**
     * 无模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveNoModelData()方法
     *
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param inputStream       文件流
     * @param baseDataProcessor 数据加工类基类
     * @author wyy
     */
    public static List<Map<T, T>> customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor) {
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        NoModelDataListener.setBase(baseDataProcessor);
        NoModelDataListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, noModelDataListener).sheet().doRead();
        return noModelDataListener.getDataList();
    }

    /**
     * 无模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveNoModelData()方法
     *
     * @param inputStream       文件流
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param baseDataProcessor 数据加工类基类
     * @param headRowNumber     从第几行开始读取
     */
    public static List<Map<T, T>> customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, int headRowNumber) {
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        NoModelDataListener.setBase(baseDataProcessor);
        NoModelDataListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, noModelDataListener).headRowNumber(headRowNumber).sheet().doRead();
        return noModelDataListener.getDataList();
    }

    /**
     * 有模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveData()方法
     * 默认读取行开始
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param inputStream       文件流
     * @param baseDataProcessor 数据加工类基类
     * @author wyy
     */
    public static List customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, Class clazz) {
        ReadExcelListener readExcelListener = new ReadExcelListener();
        ReadExcelListener.setBase(baseDataProcessor);
        ReadExcelListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, clazz, readExcelListener).sheet().doRead();
        return readExcelListener.getDataList();
    }


    /**
     * 有模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveData()方法
     * 自定义读取行数定义
     * @param inputStream       文件流
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param baseDataProcessor 数据加工类基类
     * @param clazz             模板
     * @param headRowNumber     读取行数开始读
     */
    public static List customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, Class clazz, int headRowNumber) {
        ReadExcelListener readExcelListener = new ReadExcelListener();
        ReadExcelListener.setBase(baseDataProcessor);
        ReadExcelListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, clazz, readExcelListener).sheet().headRowNumber(headRowNumber).doRead();
        return readExcelListener.getDataList();
    }


    /**
     * 简单的读 只读单sheet默认第一个sheet
     *
     * @param inputStream 文件流
     * @param clazz       实体类
     * @return 数据源list
     * @author wyy
     */
    public static <T> List<T> simpleReadFirstSheet(InputStream inputStream, Class<T> clazz) {
        return repeatedReadBySheetNos(inputStream, clazz, 1, 0);

    }


    /**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     *
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<T> repeatedReadToAllSheet(InputStream inputStream, Class clazz) {

        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcel.read(inputStream, clazz, dataListener).doReadAll();
        return dataListener.getDataList();

    }


    /**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * 指定sheet读取 传入0、1、2分别读取的sheet是Excel从左到右
     *
     * @param sheetNos 输入需要读取的sheet数量 想要读取多少个就输入多少
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<T> repeatedReadBySheetNos(InputStream inputStream, Class<T> clazz, int headRowNumber, Integer... sheetNos) {
        if (headRowNumber <= 0) {
            throw new RuntimeException("请输入大于零的数字");
        }
        List<T> res = new ArrayList<>();
        if(sheetNos.length == 0){
            ExcelReader excelReader = EasyExcel.read(inputStream).build();
            List<T> list = readSheet(excelReader, clazz, headRowNumber, 0);
            res.addAll(list);
        }else {
            for (Integer sheet : sheetNos) {
                ExcelReader excelReader = EasyExcel.read(inputStream).build();
                List<T> list = readSheet(excelReader, clazz, headRowNumber, sheet);
                res.addAll(list);
            }
        }
        return res;

    }


    /**
     * @param excelReader   excel读取reader
     * @param clazz         读取模板
     * @param headRowNumber 读取行数
     * @param sheet         读取的sheetNo
     * @param <T> 泛型
     * @return 返回数据集合
     * @author wyy
     */
    private static <T> List<T> readSheet(ExcelReader excelReader, Class<T> clazz, int headRowNumber, Integer sheet) {
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet build = EasyExcel.readSheet(sheet).head(clazz).registerReadListener(dataListener).headRowNumber(headRowNumber).build();
        excelReader.read(build);
        return dataListener.getDataList();
    }


    /**
     * 多行头 表头读取
     *
     * @author wyy
     * <p>1. 创建excel对应的实体对象 参照{@link Class}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>3. headRowNumber  这里设置 想要读取的表头行数 例如 默认返回1行  如果有2行表头就返回2
     */
    public static <T> List<Map<Integer, String>> complexHeaderRead(InputStream inputStream, Class<T> clazz, int headRowNumber) {
        ReadExcelListener<T> dataListener = getReadExcelListener(inputStream, clazz, headRowNumber);
        return dataListener.getHeadMapList();
    }


    /**
     *
     * @param inputStream
     * @param clazz
     * @param headRowNumber
     * @param <T>
     * @return
     */
    private static <T> ReadExcelListener<T> getReadExcelListener(InputStream inputStream, Class<T> clazz, int headRowNumber) {
        if (headRowNumber <= 0) {
            throw new RuntimeException("请输入大于零的数字");
        }
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(inputStream, clazz, dataListener).sheet()
                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(headRowNumber).doRead();
        return dataListener;
    }





}
