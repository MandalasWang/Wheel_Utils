package ink.boyuan.wheels.easyexcel.listen;

import ink.boyuan.wheels.easyexcel.listen.base.BaseDataProcessor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;

/**
 * @author wyy
 * @version 1.0
 * @Classname BaseDataProcessorAdapter
 * @date 2021/1/27 14:38
 * @description 自定义数据加工类 可以继承此类来自定义数据集处理方式
 **/
@Slf4j
public class BaseDataProcessorAdapter<T> implements BaseDataProcessor<T> {

    @Override
    public void saveNoModelData(List<Map<T, T>> list) {
        System.out.println("自定义加工类  数据集" );
    }

    @Override
    public void saveData(List<T> list) {
        System.out.println("自定义加工类  数据集" );
    }
}
