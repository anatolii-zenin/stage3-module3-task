package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorEntity;
import com.mjc.school.repository.model.NewsEntity;

import java.util.List;

public interface DataSource {
    List<AuthorEntity> getAllAuthors();
    List<NewsEntity> getAllNews();
}
