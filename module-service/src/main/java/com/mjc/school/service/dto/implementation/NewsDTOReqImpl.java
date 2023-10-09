package com.mjc.school.service.dto.implementation;

import com.mjc.school.service.dto.NewsDTOReq;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("newsDtoReqImpl")
public class NewsDTOReqImpl implements NewsDTOReq {
    protected Long id;
    protected String title;
    protected String content;
    protected Long authorId;
}
