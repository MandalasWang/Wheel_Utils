package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.EmailFormatCheck;
import ink.boyuan.wheels.annotation.config.ValidParamConfig;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author wyy
 * @version 1.0
 * @Classname EmailFormatCheckValidator
 * @date 2021/1/28 10:58
 * @description
 **/
@Slf4j
public class EmailFormatCheckValidator implements ConstraintValidator<EmailFormatCheck, String> {


    private ValidParamConfig paramConfig = new ValidParamConfig();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String regex = paramConfig.getEmailFormat();
        if (Pattern.matches(regex, s)) {
            return true;
        }
        log.info("邮件格式校验不通过");
        return false;
    }


}
