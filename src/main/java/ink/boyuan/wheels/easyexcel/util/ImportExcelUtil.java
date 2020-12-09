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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 2.0
 * @Classname ImportExcelUtil
 * @date 2020/11/27 14:44
 * @description  主要用于读取Excel 可以指定从第几行开始读取
 **/
public class ImportExcelUtil implements BaseUtil {





    /*************************
     * 报表读取工具类 包含以下方法
     * 1、读取表头 headerRead
     * 2、简单读取单sheet 默认第一个sheet simpleReadFirstSheet
     * 3、一次性读取所有sheet 不指定sheet repeatedReadToAllSheet
     * 4、指定sheetNO 读取   repeatedReadBySheetNos
     * 5、多行表头复杂表头读取  complexHeaderRead
     * ...
     *
     */

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelUtil.class);

    private static ImportExcelUtil instance;



    private ImportExcelUtil(){

    }


    public static ImportExcelUtil getInstance(){
        //判断是否是空 第一重判断
        if(null == instance){
            //加锁防止并发
            synchronized (ImportExcelUtil.class){
                if(null == instance){
                    instance = new ImportExcelUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 读取表头数据
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T>List<Map<Integer,String>> headerRead(InputStream inputStream, Class<T> clazz) {
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(inputStream, clazz, dataListener).sheet().doRead();
        return dataListener.getHeadMapList();
    }



    /**
     * 简单的读 只读单sheet默认第一个sheet
     * @author wyy
     * @param inputStream 文件流
     * @param clazz    实体类
     * @return 数据源list
     */
    public static <T>List<T> simpleReadFirstSheet(InputStream inputStream, Class<T> clazz){
        return repeatedReadBySheetNos(inputStream,clazz,1,0);

    }



    /**
     * 无模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveNoModelData()方法
     *
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param inputStream       文件流
     * @param baseDataProcessor 数据加工类基类
     * @author wyy
     */
    public static void customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor) {
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        NoModelDataListener.setBase(baseDataProcessor);
        NoModelDataListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, noModelDataListener).sheet().doRead();

    }

    /**
     * 无模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveNoModelData()方法
     *
     * @param inputStream       文件流
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param baseDataProcessor 数据加工类基类
     * @param headRowNumber     从第几行开始读取
     */
    public static void customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, int headRowNumber) {
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        NoModelDataListener.setBase(baseDataProcessor);
        NoModelDataListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, noModelDataListener).headRowNumber(headRowNumber).sheet().doRead();

    }

    /**
     * 有模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveData()方法
     *
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param inputStream       文件流
     * @param baseDataProcessor 数据加工类基类
     * @author wyy
     */
    public static void customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, Class clazz) {
        ReadExcelListener readExcelListener = new ReadExcelListener();
        ReadExcelListener.setBase(baseDataProcessor);
        ReadExcelListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, clazz, readExcelListener).sheet().doRead();
    }


    /**
     * 有模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveData()方法
     *
     * @param inputStream       文件流
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param baseDataProcessor 数据加工类基类
     * @param clazz             模板
     * @param headRowNumber     读取行数开始读
     */
    public static void customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor, Class clazz, int headRowNumber) {
        ReadExcelListener readExcelListener = new ReadExcelListener();
        ReadExcelListener.setBase(baseDataProcessor);
        ReadExcelListener.setBatchCount(onceReadMaxCount);
        EasyExcel.read(inputStream, clazz, readExcelListener).sheet().doRead();
    }

    /**
     * 简单的无模板读取数据
     * @author wyy
     * @param inputStream 文件流
     * @return 数据源list
     */
    public static List<Map<T, T>> noModelRead(InputStream inputStream){
        NoModelDataListener noModelDataListener = new NoModelDataListener();
        EasyExcel.read(inputStream,noModelDataListener).sheet().doRead();
        return noModelDataListener.getList();

    }


    /**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T>List<T> repeatedReadToAllSheet(InputStream inputStream, Class clazz) {
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 读取全部sheet
        // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcel.read(inputStream, clazz, dataListener).doReadAll();
        return dataListener.getList();

    }


    /**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * 指定sheet读取 传入0、1、2分别读取的sheet是Excel从左到右
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     * @param sheetNos  输入需要读取的sheet 想要读取那个就输入哪个
     */
    public static <T>List<T> repeatedReadBySheetNos(InputStream inputStream, Class<T> clazz, int headRowNumber, Integer ...sheetNos) {
        if(headRowNumber <= 0){
            throw new RuntimeException("请输入大于零的数字");
        }
        ExcelReader excelReader  = EasyExcel.read(inputStream).build();
        List<T> res = new ArrayList<>();
        for(Integer sheet:sheetNos){
            List<T> list = readSheet(excelReader,clazz, headRowNumber, sheet);
            res.addAll(list);
        }
        return res;

    }


    /**
     * @author wyy
     * @param excelReader  excel读取reader
     * @param clazz      读取模板
     * @param headRowNumber  读取行数
     * @param sheet    读取的sheetNo
     * @return 返回集合
     */
    private static <T> List<T> readSheet( ExcelReader excelReader,Class<T> clazz, int headRowNumber, Integer sheet) {
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet build = EasyExcel.readSheet(sheet).head(clazz).registerReadListener(dataListener).headRowNumber(headRowNumber).build();
        excelReader.read(build);
        return dataListener.getList();
    }

    /**
     * 多行头
     * @author wyy
     * <p>1. 创建excel对应的实体对象 参照{@link Class}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>3. 设置headRowNumber参数，然后读。 这里要注意headRowNumber如果不指定， 会根据你传入的class的{@link @ExcelProperty#value()}里面的表头的数量来决定行数，
     * 如果不传入class则默认为1.当然你指定了headRowNumber不管是否传入class都是以你传入的为准。
     */
    public static <T>List<T> complexHeaderRead(InputStream inputStream, Class<T> clazz, int headRowNumber) {
        if(headRowNumber <= 0){
            throw new RuntimeException("请输入大于零的数字");
        }
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(inputStream, clazz,dataListener).sheet()
                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(headRowNumber).doRead();
        return dataListener.getList();
    }



    /**
     * 同步的返回，不推荐使用，如果数据量大会把数据放到内存里面
     * @author wyy
     */
    @Deprecated
    public static void synchronousRead(InputStream inputStream, Class<T> clazz, int headRowNumber)  {
        if(headRowNumber <= 0){
            throw new RuntimeException("请输入大于零的数字");
        }
        ReadExcelListener<T> dataListener = new ReadExcelListener<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<T> list = EasyExcel.read(inputStream,dataListener).head(clazz).headRowNumber(headRowNumber).sheet().doReadSync();
        for (T data : list) {
            LOGGER.info("读取到数据:{}", JSON.toJSONString(data));
        }


        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<T, T>> listMap = EasyExcel.read(inputStream,dataListener).sheet().doReadSync();
        for (Map<T, T> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            LOGGER.info("读取到数据:{}", JSON.toJSONString(data));
        }
    }







}
