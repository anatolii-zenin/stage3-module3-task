package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component("newsDtoReq")
public class NewsDTOReq {
    private Long id;
    private String title;
    private String content;
    @Autowired
    private AuthorDTOReq author;
    @Autowired
    private List<TagDTOReq> tags;
}
