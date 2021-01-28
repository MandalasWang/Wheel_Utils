package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.EmailFormatCheckValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wyy
 * @version 1.0
 * @Classname EmailFormatCheck
 * @date 2021/1/18 10:07
 * @description 校验邮件格式是否正常 格式：xxx@sina.com，xxx@xxx.com.cn
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
@Constraint(validatedBy = EmailFormatCheckValidator.class)
public @interface EmailFormatCheck {

    /**
     * 错误信息
     * @return
     */
    String message() default "邮件格式错误";

    /**
     * 校验格式
     * @return
     */
    String regex() default "";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
