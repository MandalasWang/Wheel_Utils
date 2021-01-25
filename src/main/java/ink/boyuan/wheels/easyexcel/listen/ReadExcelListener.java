package ink.boyuan.wheels.easyexcel.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import ink.boyuan.wheels.easyexcel.constant.ReadConstant;
import ink.boyuan.wheels.easyexcel.listen.base.BaseDataProcessor;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @Classname ReadExcelListener
 * @date 2020/11/30 9:34
 * @description
 **/
@Slf4j
public class ReadExcelListener<T> extends AnalysisEventListener<T> {




    /**
     * 最大一次处理条数，实际使用中可以3000条（推荐），然后清理list ，方便内存回收
     * 不推荐一次性读取所有并处理 这样很费内存并有很大可能性造成OOM
     */
    private static int BATCH_COUNT = ReadConstant.MAX_READ_COUNTS;
    /**
     * 數據集合
     */
    private List<T> dataList = new ArrayList<>();


    /**
     * 自定义实现监听数据处理方法
     */
    private static BaseDataProcessor listener;


    /**
     * 表头集合
     */
    private List<Map<Integer, String>> headMapList = new ArrayList<>();

    /**
     * 自定义读取行数一次性 读完后会对list进行清空操作
     * 注意需求大小
     *
     * @param maxCount 一次处理的最大行数
     */
    public static void setBatchCount(int maxCount) {
        if (maxCount <= 1) {
            throw new ArithmeticException("请输入大于0的行数");
        }
        BATCH_COUNT = maxCount;
    }

    public List<Map<Integer, String>> getHeadMapList() {
        return headMapList;
    }

    /**
     * 设置自定义监听器入参
     *
     * @param baseDataProcessor 加工类基类
     */
    public static void setBase(BaseDataProcessor baseDataProcessor) {
        listener = baseDataProcessor;
    }

    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 数据保存方法传入自定义实现类并实现saveData方法
     */
    private static void saveData(List list) {
        log.info("可以自定义listener实现BaseListener中的saveData方法  在这里书写对解析的数据进行处理的代码！");
        listener.saveData(list);
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context 上下文
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        dataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (dataList.size() >= BATCH_COUNT) {
            saveData(dataList);
            // 存储完成清理 list
            dataList.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context 上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("所有数据解析完成！");
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap 头信息
     * @param context 上下文
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
        headMapList.add(headMap);
        //达到一次读取上限就进行数据保存操作推荐一次保存3000条
        if (headMapList.size() >= BATCH_COUNT) {
            headMapList.clear();
        }
    }
}
