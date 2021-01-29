package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.MoneyFormatCheck;
import ink.boyuan.wheels.annotation.config.ValidParamConfig;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * @author wyy
 * @version 1.0
 * @Classname MoneyFormatCheckValidator
 * @date 2021/1/28 17:20
 * @description
 **/
public class MoneyFormatCheckValidator implements ConstraintValidator<MoneyFormatCheck, BigDecimal> {

    private ValidParamConfig validParamConfig = new ValidParamConfig();

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        if(null == bigDecimal || bigDecimal.compareTo(BigDecimal.ZERO) == 0){
            return false;
        }
        String regex = validParamConfig.getMoneyFormat();
        return Pattern.matches(regex, String.valueOf(bigDecimal));
    }
}
