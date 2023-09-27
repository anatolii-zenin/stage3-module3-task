package com.mjc.school.service.validator;

import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDTOReq;
import org.springframework.stereotype.Component;

@Component
public class NewsValidator implements Validator<NewsDTOReq> {
    private NewsService newsService;
    private int titleLengthFrom = 3;
    private int titleLengthTo = 30;
    private int contentLengthFrom = 3;
    private int contentLengthTo = 255;
    @Override
    public boolean validate(NewsDTOReq req) {
        StringBuilder errors = new StringBuilder();
        if (req.getId() != null && newsService.readById(req.getId()) == null)
            errors.append("News with id " + req.getId() + " does not exist.\n");
        if (!validateRange(req.getTitle().length(),titleLengthFrom, titleLengthTo))
            errors.append("Title length should be between " + titleLengthFrom + " and " +
                    titleLengthTo + " characters. Current length: " + req.getTitle().length() + ".\n");
        if (!validateRange(req.getContent().length(), contentLengthFrom, contentLengthTo))
            errors.append("Title length should be between " + contentLengthFrom + " and " +
                    contentLengthTo + " characters. Current length: " + req.getContent().length() + ".\n");
        if (newsService.readById(req.getAuthorId()) == null)
            errors.append("Author with id " + req.getAuthorId() + " does not exist.\n");
        if (errors.length() > 0)
            throw new RuntimeException(errors.toString());
        return true;
    }

    private boolean validateRange(int value, int from, int to) {
        return value > from && value < to;
    }

    public NewsValidator(NewsService newsService) {
        this.newsService = newsService;
    }
}
