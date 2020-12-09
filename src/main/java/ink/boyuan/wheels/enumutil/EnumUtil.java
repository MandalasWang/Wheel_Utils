package ink.boyuan.wheels.enumutil;

import ink.boyuan.wheels.base.BaseUtil;
import ink.boyuan.wheels.enumutil.enums.CodeEnum;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/10/23 16:51
 *  枚举工具类
 **/
public class EnumUtil implements BaseUtil {


    /**
     * 返回的对象实现CodeEnum接口
     */
    public static <T extends CodeEnum> T getByCode(Class<T> enumClass, String code) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

    public static <T extends CodeEnum> T getByType(Class<T> enumClass, String type) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getType().equals(type)) {
                return each;
            }
        }
        return null;
    }
}
