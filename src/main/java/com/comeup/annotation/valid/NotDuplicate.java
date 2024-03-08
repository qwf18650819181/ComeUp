package com.comeup.annotation.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotDuplicateValidator.class)
// 标明由哪个类执行校验逻辑
public @interface NotDuplicate {
    // 校验出错时默认返回的消息
    String message() default "重复内容";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
