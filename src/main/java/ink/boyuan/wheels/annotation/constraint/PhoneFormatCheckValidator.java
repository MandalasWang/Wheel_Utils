package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.PhoneFormatCheck;
import ink.boyuan.wheels.annotation.config.ValidParamConfig;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author wyy
 * @version 1.0
 * @Classname PhoneFormatCheckValidator
 * @date 2021/1/28 14:14
 * @description
 **/
public class PhoneFormatCheckValidator implements ConstraintValidator<PhoneFormatCheck, String> {

    private ValidParamConfig paramConfig = new ValidParamConfig();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String regex =  paramConfig.getPhoneFormat();
        return Pattern.matches(regex,s);
    }
}
