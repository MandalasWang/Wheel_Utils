package ink.boyuan.wheels.easyexcel.listen.base;



import java.util.List;
import java.util.Map;


/**
 * @author wyy
 * @version 1.0
 * @date 2020/12/8 11:07
 * 对读取数据进行处理的类 需要自定义数据处理类可以实现接口
 **/
public interface BaseDataProcessor<T> {


    /**
     * 处理无模板数据方法用户可实现该方法进行数据处理
     *
     * @param list
     */
    void saveNoModelData(List<Map<T, T>> list);


    /**
     * 处理有模板数据方法用户可实现该方法进行数据处理
     *
     * @param list
     */
    void saveData(List<T> list);
}
