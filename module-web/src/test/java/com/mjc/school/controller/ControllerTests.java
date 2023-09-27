package com.mjc.school.controller;

import com.mjc.school.controller.implementation.AuthorControllerImpl;
import com.mjc.school.controller.implementation.NewsControllerImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class ControllerTests {
    private NewsController newsController;
    private AuthorController authorController;
    private static AnnotationConfigApplicationContext context;
    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(ControllerSpringConfig.class);
    }
    @AfterAll
    public static void tearDown() {
        context.close();
    }
    @Test
    public void newsRepoTest() {
        newsController = context.getBean(NewsControllerImpl.class);
        var allNews = newsController.readAll();
        assertEquals("First entry is not as expected", "GENERAL PROVISIONS", allNews.get(0).getTitle());
        assertEquals("The number of entries is not as expected", 30, allNews.size());
    }

    @Test
    public void authorRepoTest() {
        authorController = context.getBean(AuthorControllerImpl.class);
        var allAuthors = authorController.readAll();
        assertEquals("First entry is not as expected", "William Shakespeare", allAuthors.get(0).getName());
        assertEquals("The number of entries is not as expected", 30, allAuthors.size());
    }
}