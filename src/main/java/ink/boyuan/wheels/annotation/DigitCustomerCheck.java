package ink.boyuan.wheels.annotation;


import ink.boyuan.wheels.annotation.constraint.DigitCustomerCheckValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author wyy
 * 数字自定义校验
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Constraint(validatedBy = DigitCustomerCheckValidator.class)
public @interface DigitCustomerCheck {

    String message() default "输入格式不合法";

    /**
     * 输入长度包括小数点以及小数位数
     * @return
     */
    String value() default "1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
