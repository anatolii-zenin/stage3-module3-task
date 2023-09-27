package com.mjc.school.repository.model;

import java.time.LocalDateTime;

public interface NewsEntity extends BaseEntity<Long> {
    String getTitle();
    void setTitle(String title);
    String getContent();
    void setContent(String content);
    LocalDateTime getCreateDate();
    void setCreateDate(LocalDateTime createDate);
    LocalDateTime getLastUpdateDate();
    void setLastUpdateDate(LocalDateTime lastUpdateDate);
    Long getAuthorId();
    void setAuthorId(Long authorId);
}
