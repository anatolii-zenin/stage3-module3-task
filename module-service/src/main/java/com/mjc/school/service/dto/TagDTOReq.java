package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component("tagDtoReq")
public class TagDTOReq {
    private Long id;
    private String name;
}
