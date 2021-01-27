package ink.boyuan.wheels.annotation;


import ink.boyuan.wheels.annotation.constraint.DateConstraintValidator;
import ink.boyuan.wheels.enumutil.enums.FormatEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 日期校验注解
 *
 * @author yhc
 * @date 2020/12/8 15:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateConstraintValidator.class)
public @interface DateValid {

    String message() default "时间格式错误";

    /**
     * 自定义时间格式-默认为yyyy-MM-dd HH:mm:ss
     */
    FormatEnum format() default FormatEnum.DATE_TIME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
