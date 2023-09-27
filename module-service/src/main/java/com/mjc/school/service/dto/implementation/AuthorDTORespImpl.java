package com.mjc.school.service.dto.implementation;

import com.mjc.school.service.dto.AuthorDTOResp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Component
public class AuthorDTORespImpl extends AuthorDTOReqImpl implements AuthorDTOResp {
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private static final String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public String toString() {
        return "[" + id + "] " + name + ". created: " +
                createDate.format(DateTimeFormatter.ofPattern(dateFormatPattern)) + ". modified: " +
                lastUpdateDate.format(DateTimeFormatter.ofPattern(dateFormatPattern));
    }
}
