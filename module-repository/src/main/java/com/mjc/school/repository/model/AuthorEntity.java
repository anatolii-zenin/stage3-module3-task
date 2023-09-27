package com.mjc.school.repository.model;

import java.time.LocalDateTime;

public interface AuthorEntity extends BaseEntity<Long> {
    String getName();
    void setName(String name);
    LocalDateTime getCreateDate();
    void setCreateDate(LocalDateTime createDate);
    LocalDateTime getLastUpdateDate();
    void setLastUpdateDate(LocalDateTime lastUpdateDate);
}
