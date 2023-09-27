package com.mjc.school.service.validator;

import com.mjc.school.service.AuthorService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.service.dto.implementation.NewsDTOReqImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configurable
public class ValidatorAspect {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private NewsService newsService;
    @Before("execution(public * *(.., @com.mjc.school.service.validator.Validate (*), ..)) && args(arg)")
    public void validate(Object arg) {
        System.out.println("Validating input...");
        var objClass = arg.getClass();
        Validator validator;
        if (objClass.equals(NewsDTOReqImpl.class))
            validator = new NewsValidator(newsService);
        else if (objClass.equals(AuthorDTOReqImpl.class))
            validator = new AuthorValidator(authorService);
        else
            throw new RuntimeException("Validator unable to process class: " + objClass);
        if (!validator.validate(arg))
            throw new RuntimeException("Validation failed");
        System.out.println("Validation passed.");
    }
}
