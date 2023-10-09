package com.mjc.school.service.dto.implementation;

import com.mjc.school.service.dto.AuthorDTOReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component("authorDtoReqImpl")
public class AuthorDTOReqImpl implements AuthorDTOReq {
    protected Long id;
    protected String name;
}
