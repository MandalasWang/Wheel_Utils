package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.EnumValid;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

/**
 * 枚举校验实现-Integer
 *
 * @author yhc
 * @date 2020/12/8 15:14
 */
public class EnumIntegerConstraintValidator implements ConstraintValidator<EnumValid, Integer> {
    /**
     * 枚举类
     */
    private Class<?> cls;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 为空返回true
        if (ObjectUtils.isEmpty(value)) {
            return true;
        }
        // 不是枚举返回false
        if (!cls.isEnum()) {
            return false;
        }
        try {
            return isEnum(cls, value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean isEnum(Class<?> cl, Integer value) throws Exception {
        //枚举类验证
        Object[] enums = cl.getEnumConstants();
        Method method = cl.getMethod("getCode");
        for (Object obj : enums) {
            Object code = method.invoke(obj);
            if (value.equals(Integer.parseInt(code.toString()))) {
                return true;
            }
        }
        return false;
    }

}