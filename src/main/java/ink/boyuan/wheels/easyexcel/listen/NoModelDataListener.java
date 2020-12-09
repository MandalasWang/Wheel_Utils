package ink.boyuan.wheels.easyexcel.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import ink.boyuan.wheels.easyexcel.constant.ReadConstant;
import ink.boyuan.wheels.easyexcel.listen.base.BaseDataProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @date 2020/12/8 10:23
 * 无模板监听器
 **/
@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<T, T>> {

    /**
     * 最大一次处理条数，实际使用中可以3000条（推荐），然后清理list ，方便内存回收
     * 不推荐一次性读取所有并处理 这样很费内存并有很大可能性造成OOM
     */
    private static int BATCH_COUNT = ReadConstant.MAX_READ_COUNTS;

    List<Map<T, T>> list = new ArrayList<>();

    public List<Map<T, T>> getList() {
        return list;
    }

    /**
     * 自定义实现监听数据处理方法
     */
    private static BaseDataProcessor listener;
    /**
     * 自定义读取行数一次性 读完后会对list进行清空操作
     * 注意需求大小
     *
     * @param maxCount
     */
    public static void setBatchCount(int maxCount) {
        if (maxCount <= 1) {
            throw new ArithmeticException("请输入大于0的行数");
        }
        BATCH_COUNT = maxCount;
    }

    /**
     * 数据保存方法传入自定义实现类并实现saveData方法
     *
     * @param list 集合
     */
    private static void saveData(List<Map<T, T>> list) {
        log.info("可以自定义listener实现BaseListener中的saveData方法  在这里书写对解析的数据进行处理的代码！");
        listener.saveNoModelData(list);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    /**
     * 设置自定义入参processor加工类
     *
     * @param baseDataProcessor 加工类
     */
    public static void setBase(BaseDataProcessor baseDataProcessor) {
        listener = baseDataProcessor;
    }

    @Override
    public void invoke(Map<T, T> integerStringMap, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(integerStringMap));
        list.add(integerStringMap);
        //达到一次读取上限就进行数据保存操作推荐一次保存3000条
        if (list.size() >= BATCH_COUNT) {
            saveData(list);
            list.clear();
        }
    }
}
