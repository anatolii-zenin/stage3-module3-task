package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class AuthorServiceTests {
    private AuthorService authorService;

    private static AnnotationConfigApplicationContext context;
    @BeforeEach
    public void setUp() {
        context = new AnnotationConfigApplicationContext(ServiceSpringConfig.class);
    }
    @AfterEach
    public void tearDown() {
        context.close();
    }

    @Test
    public void CreateReadAuthorTest() {
        String authorName1 = "testAuthor1";
        String authorName2 = "testAuthor2";

        authorService = context.getBean(AuthorServiceImpl.class);
        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName1);
        authorService.create(authorReq);
        authorReq.setName(authorName2);
        authorService.create(authorReq);
        var entries = authorService.readAll();
        assertEquals("First entry is not as expected", authorName1, entries.get(0).getName());
        assertEquals("Second entry is not as expected", authorName2, entries.get(1).getName());

        var entryById = authorService.readById(1L);
        assertEquals("Entry by ID is not as expected", authorName1, entryById.getName());
    }

    @Test
    public void UpdateAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);

        String authorName = "testAuthor";
        String authorNameUpdated = "testAuthorUpdated";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName);
        var id = authorService.create(authorReq).getId();
        var entry = authorService.readById(id);
        assertEquals("Created entry is not as expected", authorName, entry.getName());

        authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorNameUpdated);
        authorReq.setId(id);
        authorService.update(authorReq);
        entry = authorService.readById(id);
        assertEquals("Updated entry is not as expected", authorNameUpdated, entry.getName());
    }

    @Test
    public void DeleteAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);

        String authorName = "testAuthor";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName);
        var id = authorService.create(authorReq).getId();
        var entry = authorService.readById(id);
        assertEquals("Created entry is not as expected", authorName, entry.getName());

        authorService.deleteById(id);
        entry = authorService.readById(id);
        assertEquals("Entry is not properly deleted", null, entry);
    }
}
