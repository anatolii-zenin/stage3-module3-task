package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public interface NewsDTOResp extends NewsDTOReq {
    void setCreateDate(LocalDateTime localDateTime);
    LocalDateTime getCreateDate();
    void setLastUpdateDate(LocalDateTime lastUpdateDate);
    LocalDateTime getLastUpdateDate();
}
