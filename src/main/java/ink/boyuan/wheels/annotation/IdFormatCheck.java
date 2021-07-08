package ink.boyuan.wheels.annotation;

import ink.boyuan.wheels.annotation.constraint.IdCardCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 身份证校验注解
 * @author wyy
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Constraint(validatedBy = IdCardCheckValidator.class)
public @interface IdFormatCheck {


    String message() default "身份证格式不合法";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
