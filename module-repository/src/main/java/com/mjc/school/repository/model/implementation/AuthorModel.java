package com.mjc.school.repository.model.implementation;

import com.mjc.school.repository.model.AuthorEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Setter
@Getter
public class AuthorModel implements AuthorEntity {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    @Override
    public boolean equals(Object authorEntityObj) {
        if (!(authorEntityObj instanceof AuthorModel otherAuthor))
            return false;
        return this.id.equals(otherAuthor.id) && this.name.equals(otherAuthor.name);
    }

    @Override
    public int hashCode() {
        return id.intValue() + name.hashCode();
    }
}
