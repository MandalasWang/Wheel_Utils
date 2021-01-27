package ink.boyuan.wheels.annotation.constraint;

import ink.boyuan.wheels.annotation.ListNotEmpty;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author wyy
 * @version 1.0
 * @Classname ListNotEmptyValidator
 * @date 2021/1/18 10:08
 * @description
 **/
public class ListNotEmptyValidator implements ConstraintValidator<ListNotEmpty, List> {


    @Override
    public void initialize(ListNotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(value)) {
            return false;
        }
        for (Object t : value) {
            if (t == null || "".equals(t.toString())) {
                return false;
            }
        }
        return true;
    }


}
