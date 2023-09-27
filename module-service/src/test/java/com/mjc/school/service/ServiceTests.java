package com.mjc.school.service;

import com.mjc.school.repository.datasource.implementation.DataSourceImpl;
import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import com.mjc.school.service.implementation.NewsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ServiceTests {
    private NewsService newsService;
    private AuthorService authorService;

    @Test
    public void newsRepoAccessTest() {
        newsService = new NewsServiceImpl(
            new NewsRepositoryImpl(
                new DataSourceImpl("authors", "news", "content")
            )
        );
        var allNews = newsService.readAll();
        assertEquals("First entry is not as expected", "GENERAL PROVISIONS", allNews.get(0).getTitle());
        assertEquals("The number of entries is not as expected", 30, allNews.size());

        assertEquals("Entry is not as expected", "GENERAL PROVISIONS",
                newsService.readById((long) 0).getTitle());
    }

    @Test
    public void authorRepoAccessTest() {
        authorService = new AuthorServiceImpl(
                new AuthorRepositoryImpl(
                        new DataSourceImpl("authors", "news", "content")
                )
        );
        var allAuthors = authorService.readAll();
        assertEquals("First entry is not as expected", "William Shakespeare",
                allAuthors.get(0).getName());
        assertEquals("The number of entries is not as expected", 30, allAuthors.size());

        assertEquals("Entry is not as expected", "William Shakespeare",
                authorService.readById((long) 0).getName());
    }

    @Test
    public void deleteByAuthorIdTest() {
        var dataSource = new DataSourceImpl("authors", "news", "content");
        newsService = new NewsServiceImpl(new NewsRepositoryImpl(dataSource));
        authorService = new AuthorServiceImpl(new AuthorRepositoryImpl(dataSource));
        authorService.deleteById((long) 0);
        var allAuthors = authorService.readAll();
        var allNews = newsService.readAll();
        assertEquals("First entry is not as expected", "Agatha Christie",
                allAuthors.get(0).getName());
        assertEquals("The number of entries is not as expected", 29, allAuthors.size());
        assertEquals("First entry is not as expected", "EXECUTIVE", allNews.get(0).getTitle());
        assertEquals("The number of entries is not as expected", 29, allNews.size());
    }

    @Test
    public void updateAuthorTest() {
        authorService = new AuthorServiceImpl(
                new AuthorRepositoryImpl(
                        new DataSourceImpl("authors", "news", "content")
                )
        );
        AuthorDTOReq req = new AuthorDTOReqImpl();
        req.setId((long) 1);
        req.setName("New Name");
        authorService.update(req);
        var allAuthors = authorService.readAll();
        assertEquals("New name is not as expected", "New Name",
                allAuthors.get(1).getName());
    }

    @Test
    public void createAuthorTest() {
        var dataSource = new DataSourceImpl("authors", "news", "content");
        var authorRepo1 = new AuthorRepositoryImpl(dataSource);
        var authorRepo2 = new AuthorRepositoryImpl(dataSource);
        var authorService1 = new AuthorServiceImpl(authorRepo1);
        var authorService2 = new AuthorServiceImpl(authorRepo2);

        var allAuthors = authorService1.readAll();

        var newAuthor = new AuthorDTOReqImpl();
        newAuthor.setName("testAuthor");
        authorService1.create(newAuthor);

        assertEquals("", "testAuthor", authorService2.readById((long) 30).getName());

        var allAuthorsNew = authorService2.readAll();
        assertEquals("", 1, allAuthorsNew.size() - allAuthors.size());

    }
}
