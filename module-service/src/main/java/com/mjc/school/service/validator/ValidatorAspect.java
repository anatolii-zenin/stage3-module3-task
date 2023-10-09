package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.NewsDTOReq;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configurable
public class ValidatorAspect {
    @Autowired
    ApplicationContext applicationContext;

    @Before("validate() && args(arg)")
    public void validateReq(Object arg) {
        System.out.println("Validating DTO request input...");
        var objClass = arg.getClass();
        Validator validator;
        if (objClass.equals(NewsDTOReq.class))
            validator = applicationContext.getBean(NewsValidator.class);
        else if (objClass.equals(AuthorDTOReq.class))
            validator = applicationContext.getBean(AuthorValidator.class);
        else
            throw new RuntimeException("Validator unable to process class: " + objClass);
        if (!validator.validate(arg))
            throw new RuntimeException("Validation failed");
        System.out.println("Validation passed.");
    }

    @Pointcut("execution(public * *(.., @com.mjc.school.service.validator.Validate (*), ..))")
    private void validate() {}

}
