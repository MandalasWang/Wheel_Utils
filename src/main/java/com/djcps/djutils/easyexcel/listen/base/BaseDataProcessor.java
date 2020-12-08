package com.djcps.djutils.easyexcel.listen.base;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @Classname BaseDataProcessor
 * @date 2020/12/8 11:07
 * @description
 **/
public interface BaseDataProcessor {


    /**
     * 处理数据方法
     */
    void saveNoModelData(List<Map<T, T>> list);


    /**
     * 处理数据方法
     */
    void saveData(List<T> list);
}
