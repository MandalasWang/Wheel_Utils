package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.IdFormatCheck;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证校验枚举
 * @author wyy
 */
@Slf4j
public class IdCardCheckValidator implements ConstraintValidator<IdFormatCheck, String> {


    @Override
    public void initialize(IdFormatCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = "\\\\d{17}[\\\\d|x]|\\\\d{15}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        if(m.matches()){
            return true;
        }
        return false;
    }
}
