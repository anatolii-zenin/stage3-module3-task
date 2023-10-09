package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDTOResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class NewsServiceImpl extends BaseServiceImpl<NewsDTOReq, NewsDTOResp, NewsEntity, NewsRepository>
        implements NewsService {
    @Override
    protected NewsEntity dtoToEntity(NewsDTOReq newsDTOReq) {
        return mapper.newsReqToEntity(newsDTOReq);
    }

    @Override
    protected NewsDTOResp entityToDto(NewsEntity newsEntity) {
        return mapper.newsToDto(newsEntity);
    }

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.repo = newsRepository;
    }
}
