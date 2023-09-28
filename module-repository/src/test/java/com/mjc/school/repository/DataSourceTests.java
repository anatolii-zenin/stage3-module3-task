package com.mjc.school.repository;

import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.repository.model.AuthorEntity;
import com.mjc.school.repository.model.implementation.AuthorEntityImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class DataSourceTests {
    private static AnnotationConfigApplicationContext context;
    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(RepoSpringConfig.class);
    }
    @AfterAll
    public static void tearDown() {
        context.close();
    }

    @Test
    public void readAllTest() {
        AuthorEntity authorEntity = new AuthorEntityImpl();
        AuthorRepositoryImpl authorRepository = context.getBean(AuthorRepositoryImpl.class);
        authorEntity.setName("testAuthor");
        authorRepository.create(authorEntity);
        var result = authorRepository.readAll();
//        var result = authorRepository.readById(0l);
        assertEquals("", "", result.size());
    }
    @Test
    public void repositoryTest() {
    }
}
