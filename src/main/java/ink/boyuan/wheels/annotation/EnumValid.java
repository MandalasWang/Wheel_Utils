package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.EnumIntegerConstraintValidator;
import ink.boyuan.wheels.annotation.constraint.EnumStringConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举校验注解
 *
 * @author yhc
 * @date 2020/12/8 15:16
 */
@Constraint(validatedBy = {EnumStringConstraintValidator.class, EnumIntegerConstraintValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface EnumValid {

    /**
     * 是否是多个，默认未false。为true时，传递的枚举通过逗号分隔
     */
    boolean isMultiple() default false;

    String message() default "非法请求";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> target();

}
