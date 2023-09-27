package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsController;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsControllerImpl implements NewsController {
    @Autowired
    NewsService service;

    @Override
    public List<NewsDTOResp> readAll() {
        return service.readAll();
    }

    @Override
    public NewsDTOResp readById(Long id) {
        return service.readById(id);
    }

    @Override
    public NewsDTOResp create(NewsDTOReq createRequest) {
        return service.create(createRequest);
    }

    @Override
    public NewsDTOResp update(NewsDTOReq updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }
}
