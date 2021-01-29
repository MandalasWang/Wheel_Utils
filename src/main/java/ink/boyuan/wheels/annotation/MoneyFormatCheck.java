package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.MoneyFormatCheckValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wyy
 * @version 1.0
 * @Classname MoneyFormatCheck
 * @date 2021/1/28 17:18
 * @description 金额校验注解
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Constraint(validatedBy = MoneyFormatCheckValidator.class)
public @interface MoneyFormatCheck {

    /**
     * 错误信息
     * @return
     */
    String message() default "金额格式错误";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
