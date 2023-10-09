package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("newsDtoReq")
public class NewsDTOReq {
    protected Long id;
    protected String title;
    protected String content;
    protected Long authorId;
}
