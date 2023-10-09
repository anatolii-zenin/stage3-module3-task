package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.implementation.AuthorServiceImpl;
import com.mjc.school.service.implementation.NewsServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.test.util.AssertionErrors.assertEquals;


public class NewsServiceTests {
    private NewsService newsService;
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
    public void CreateReadNewsTest() {
        authorService = context.getBean(AuthorServiceImpl.class);
        newsService = context.getBean(NewsServiceImpl.class);

        String authorName1 = "testAuthor1";
        String authorName2 = "testAuthor2";
        String newsTitle1 = "testTitle1";
        String newsTitle2 = "testTitle2";
        String newsContent1 = "testContent1";
        String newsContent2 = "testContent2";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName1);
        var authorId1 = authorService.create(authorReq).getId();
        authorReq.setName(authorName2);
        var authorId2 = authorService.create(authorReq).getId();

        NewsDTOReq newsReq = context.getBean("newsDtoReq", NewsDTOReq.class);
        newsReq.setAuthorId(1L);
        newsReq.setContent(newsContent1);
        newsReq.setTitle(newsTitle1);
        var newsId1 = newsService.create(newsReq).getId();
        newsReq.setAuthorId(2L);
        newsReq.setContent(newsContent2);
        newsReq.setTitle(newsTitle2);
        var newsId2 = newsService.create(newsReq).getId();

        var entry1 = newsService.readById(newsId1);
        var entry2 = newsService.readById(newsId2);
        assertEquals("First entry id is not as expected", newsId1, entry1.getId());
        assertEquals("First entry title is not as expected", newsTitle1, entry1.getTitle());
        assertEquals("First entry content is not as expected", newsContent1, entry1.getContent());
        assertEquals("First entry author is not as expected", authorId1, entry1.getAuthorId());
        assertEquals("Second entry id is not as expected", newsId2, entry2.getId());
        assertEquals("Second entry title is not as expected", newsTitle2, entry2.getTitle());
        assertEquals("Second entry content is not as expected", newsContent2, entry2.getContent());
        assertEquals("Second entry author is not as expected", authorId2, entry2.getAuthorId());
    }

    @Test
    public void updateNewsTest() {
        authorService = context.getBean(AuthorServiceImpl.class);
        newsService = context.getBean(NewsServiceImpl.class);

        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsTitleUpdated = "testTitleUpdated";
        String newsContent = "testContent";
        String newsContentUpdated = "testContentUpdated";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName);
        var authorId = authorService.create(authorReq).getId();
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        NewsDTOReq newsReq = context.getBean("newsDtoReq", NewsDTOReq.class);
        newsReq.setAuthorId(1L);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        var newsId = newsService.create(newsReq).getId();
        var newsEntry = newsService.readById(newsId);

        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthorId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthorId());

        newsReq = context.getBean("newsDtoReq", NewsDTOReq.class);
        newsReq.setTitle(newsTitleUpdated);
        newsReq.setContent(newsContentUpdated);
        newsReq.setId(newsId);
        newsReq.setAuthorId(authorId);
        newsService.update(newsReq);
        newsEntry = newsService.readById(newsId);

        assertEquals("Updated id is not as expected", newsId, newsEntry.getAuthorId());
        assertEquals("Updated title is not as expected", newsTitleUpdated, newsEntry.getTitle());
        assertEquals("Updated content is not as expected", newsContentUpdated, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthorId());
    }

    @Test
    public void deleteNewsTest() {
        authorService = context.getBean(AuthorServiceImpl.class);
        newsService = context.getBean(NewsServiceImpl.class);

        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsContent = "testContent";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName);
        var authorId = authorService.create(authorReq).getId();
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        NewsDTOReq newsReq = context.getBean("newsDtoReq", NewsDTOReq.class);
        newsReq.setAuthorId(1L);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        var newsId = newsService.create(newsReq).getId();
        var newsEntry = newsService.readById(newsId);
        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthorId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthorId());

        newsService.deleteById(newsId);
        newsEntry = newsService.readById(newsId);
        assertEquals("News entry is not properly deleted", null, newsEntry);

        authorEntry = authorService.readById(authorId);
        assertEquals("Author is not persisted after deleting news", authorName, authorEntry.getName());
    }

    @Test
    public void deleteNewsByDeletingAuthorTest() {
        authorService = context.getBean(AuthorServiceImpl.class);
        newsService = context.getBean(NewsServiceImpl.class);

        String authorName = "testAuthor";
        String newsTitle = "testTitle";
        String newsContent = "testContent";

        AuthorDTOReq authorReq = context.getBean("authorDtoReq", AuthorDTOReq.class);
        authorReq.setName(authorName);
        var authorId = authorService.create(authorReq).getId();
        var authorEntry = authorService.readById(authorId);
        assertEquals("Created entry is not as expected", authorName, authorEntry.getName());

        NewsDTOReq newsReq = context.getBean("newsDtoReq", NewsDTOReq.class);
        newsReq.setAuthorId(1L);
        newsReq.setContent(newsContent);
        newsReq.setTitle(newsTitle);
        var newsId = newsService.create(newsReq).getId();
        var newsEntry = newsService.readById(newsId);
        assertEquals("Entry id is not as expected", newsId, newsEntry.getAuthorId());
        assertEquals("Entry title is not as expected", newsTitle, newsEntry.getTitle());
        assertEquals("Entry content is not as expected", newsContent, newsEntry.getContent());
        assertEquals("Entry author is not as expected", authorId, newsEntry.getAuthorId());

        authorService.deleteById(authorId);
        authorEntry = authorService.readById(authorId);
        assertEquals("Author is not deleted", null, authorEntry);
        newsEntry = newsService.readById(newsId);
        assertEquals("News entry is not properly deleted after deleting author", null, newsEntry);
    }
}
