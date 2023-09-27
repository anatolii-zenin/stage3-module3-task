package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.NewsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Setter
@Getter
public class NewsModel implements NewsEntity {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    @Override
    public boolean equals(Object newsEntityObj) {
        if (!(newsEntityObj instanceof NewsModel otherNews))
            return false;
        return this.id.equals(otherNews.id);
    }

    @Override
    public int hashCode() {
        return id.intValue() + title.hashCode() + content.hashCode();
    }
}
