package com.mjc.school.controller.util;

import com.mjc.school.service.AuthorService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class DemoDataLoader {
    @Autowired
    AuthorService authorService;
    @Autowired
    NewsService newsService;

    @Autowired
    ApplicationContext applicationContext;

    @Transactional
    public void populateAuthors() {
        var authorLines = readAuthorLines();
        for (String authorLine : authorLines) {
            AuthorDTOReq author = (AuthorDTOReq) applicationContext.getBean("authorDtoReqImpl");
            author.setName(authorLine);

            authorService.create(author);
        }
    }

    @Transactional
    public void populateNews() {
        var titleLines = readTitleLines();
        var contentLines = readContentLines();
        for (int i=0; i < titleLines.size(); i++) {
            NewsDTOReq news = (NewsDTOReq) applicationContext.getBean("newsDtoReqImpl");
            news.setTitle(titleLines.get(i));
            news.setContent(contentLines.get(i));
            news.setAuthorId((long) i+1);
            var id = newsService.create(news);
            System.out.println(id);
        }
    }

    private String titleFileName = "news";
    private String contentFileName = "content";
    private String authorFileName = "authors";

    private List<String> readFile(String filename) {
        List<String> lines;
        try(var inputStream = this.getClass().getResourceAsStream("/" + filename)) {
            assert inputStream != null;
            lines = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    private List<String> readTitleLines() {
        return readFile(titleFileName);
    }

    private List<String> readContentLines() {
        return readFile(contentFileName);
    }

    private List<String> readAuthorLines() {
        return readFile(authorFileName);
    }
}
