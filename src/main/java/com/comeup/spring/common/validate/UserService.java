package com.comeup.spring.common.validate;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.List;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月13日 0013
 * @version: 1.0
 */
@Service
public class UserService {


    public String test() {
        User user = new User();
        user.setName(null);
        user.setAge(0);
        user.setSex("");
        user.setAddress("");
        user.setUserAddress("");
        // 创建Errors对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        UserValidator userValidator = new UserValidator();
        // 进行验证
        ValidationUtils.invokeValidator(userValidator, user, errors);
        // 处理验证结果
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(System.out::println);
        } else {
            System.out.println("User is valid.");
        }

        ovalValid(user);
        return "success";
    }

    public void ovalValid(User user) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(user);
        for (ConstraintViolation violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
