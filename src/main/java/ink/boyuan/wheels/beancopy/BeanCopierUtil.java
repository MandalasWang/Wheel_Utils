package ink.boyuan.wheels.beancopy;

import net.sf.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dxl
 */
public class BeanCopierUtil {

    /**
     * BeanCopier 缓存
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 将源对象属性复制到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {

        String key = source.getClass().toString() + target.getClass().toString();
        if (!BEAN_COPIER_MAP.containsKey(key)) {
            synchronized (BeanCopierUtil.class) {
                if (!BEAN_COPIER_MAP.containsKey(key)) {
                    BeanCopier beanCopier = BeanCopier.create(source.getClass(),
                            target.getClass(), false);
                    BEAN_COPIER_MAP.put(key, beanCopier);
                }
            }
        }

        BeanCopier beanCopier = BEAN_COPIER_MAP.get(key);
        beanCopier.copy(source, target, null);
    }
    private BeanCopierUtil(){

    }
}
