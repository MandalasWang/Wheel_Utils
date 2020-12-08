package com.djcps.djutils.easyexcel.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.djcps.djutils.easyexcel.constant.ReadConstant;
import com.djcps.djutils.easyexcel.listen.base.BaseListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @Classname NoModelDataListener
 * @date 2020/12/8 10:23
 * @description
 **/
@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<T, T>> implements BaseListener {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条（推荐），然后清理list ，方便内存回收
     * 不推荐一次性读取所有并处理 这样很费内存并有很大可能性造成OOM
     */
    private static final int BATCH_COUNT = ReadConstant.MAX_READ_COUNTS;

    List<Map<T, T>> list = new ArrayList<>();

    public List<Map<T, T>> getList() {
        return list;
    }

    @Override
    public void invoke(Map<T, T> integerStringMap, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(integerStringMap));
        list.add(integerStringMap);
        if (list.size() >= BATCH_COUNT) {
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }


    @Override
    public void saveData() {
        log.info("这里书写对解析的数据进行处理的代码！");
    }
}
