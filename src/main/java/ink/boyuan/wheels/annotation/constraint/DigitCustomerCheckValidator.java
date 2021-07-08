package ink.boyuan.wheels.annotation.constraint;


import ink.boyuan.wheels.annotation.DigitCustomerCheck;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DigitCustomerCheckValidator implements ConstraintValidator<DigitCustomerCheck, String> {

    private int lon;

    @Override
    public void initialize(DigitCustomerCheck constraintAnnotation) {
        lon = Integer.getInteger(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String match = "^([1-9][0-9]*)+(\\.[0-9]{1,2})?$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(s);
        if(lon < s.length() && matcher.matches()){
            return true;
        }
        return false;
    }
}
