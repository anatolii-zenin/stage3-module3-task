package com.mjc.school.service.dto;

public interface NewsDTOReq {
    void setId(Long id);
    Long getId();
    void setContent(String content);
    String getContent();
    void setTitle(String title);
    String getTitle();
    void setAuthorId(Long id);
    Long getAuthorId();
}
