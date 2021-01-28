package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.PhoneFormatCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wyy
 * @version 1.0
 * @Classname PhoneFormatCheck
 * @date 2021/1/28 14:13
 * @description
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Constraint(validatedBy = PhoneFormatCheckValidator.class)
public @interface PhoneFormatCheck {

    /**
     * 错误信息
     * @return
     */
    String message() default "手机格式错误";

    /**
     * 校验格式
     * @return
     */
    String regex() default "";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
