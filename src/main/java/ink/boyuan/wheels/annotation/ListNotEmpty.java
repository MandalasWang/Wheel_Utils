package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.ListNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wyy
 * @version 1.0
 * @Classname ListNotEmpty
 * @date 2021/1/18 10:07
 * @description 校验集合不为空
 **/
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Constraint(validatedBy = ListNotEmptyValidator.class)
public @interface ListNotEmpty {

    String message() default "数据不能为空";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
