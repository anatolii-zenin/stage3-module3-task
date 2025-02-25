package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.dto.TagDTOResp;
import com.mjc.school.service.mapper.NewsDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<NewsDTOReq, NewsDTOResp, NewsEntity, NewsRepository>
        implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsDTOMapper mapper;
    @Override
    protected NewsEntity dtoToEntity(NewsDTOReq newsDTOReq) {
        return mapper.newsReqToEntity(newsDTOReq);
    }

    @Override
    protected NewsDTOResp entityToDto(NewsEntity newsEntity) {
        return mapper.newsToDto(newsEntity);
    }

    @Override
    protected NewsRepository getRepo() {
        return newsRepository;
    }

    @Override
    public List<TagDTOResp> readTagsByNewsId(Long newsId) {
        return readById(newsId).getTags();
    }

    @Override
    public AuthorDTOResp readAuthorByNewsId(Long newsId) {
        return readById(newsId).getAuthor();
    }

    @Override
    public List<NewsDTOResp> readByCriteria(NewsDTOReq req) {
        var news = newsRepository.readNewsByCriteria(dtoToEntity(req));
        var newsResps = new ArrayList<NewsDTOResp>();
        for (var entry : news)
            newsResps.add(entityToDto(entry));
        return newsResps;
    }
}
