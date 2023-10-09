package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component("authorDtoReq")
public class AuthorDTOReq {
    protected Long id;
    protected String name;
}
