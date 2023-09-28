package com.mjc.school.service;

import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.service.implementation.NewsServiceImpl;
import org.junit.jupiter.api.Test;

public class ServiceTests {
    private NewsService newsService;
    private AuthorService authorService;

    @Test
    public void newsRepoAccessTest() {
        newsService = new NewsServiceImpl(new NewsRepositoryImpl());
        var allNews = newsService.readAll();
    }
}
