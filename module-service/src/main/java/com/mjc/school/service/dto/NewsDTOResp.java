package com.mjc.school.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Component
@Qualifier("newsResp")
public class NewsDTOResp extends NewsDTOReq {
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public String toString() {
        return "[" + id + "] " + title + ". created: " +
                createDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". modified: " +
                lastUpdateDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". author: " +
                authorId + ". " + content;
    }
}
