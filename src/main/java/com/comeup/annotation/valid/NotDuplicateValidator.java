package com.comeup.annotation.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class NotDuplicateValidator implements ConstraintValidator<NotDuplicate, List<String>> {

    @Override
    public void initialize(NotDuplicate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value.stream().distinct().count() == value.size();
    }

}
