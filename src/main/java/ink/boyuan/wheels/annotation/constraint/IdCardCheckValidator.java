package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.IdFormatCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 身份证校验枚举
 * @author wyy
 */
public class IdCardCheckValidator implements ConstraintValidator<IdFormatCheck, String> {


    @Override
    public void initialize(IdFormatCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean a = s.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
        boolean b = s.matches("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$");
        if(a || b){
            return true;
        }
        return false;
    }
}
