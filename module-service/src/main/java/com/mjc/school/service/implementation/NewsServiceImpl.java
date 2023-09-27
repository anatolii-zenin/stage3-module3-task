package com.mjc.school.service.implementation;

import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import com.mjc.school.service.dto.implementation.NewsDTOReqImpl;
import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsEntity;
import com.mjc.school.repository.model.implementation.NewsModel;
import com.mjc.school.service.DTOMapper;
import com.mjc.school.service.NewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsDTOReq, NewsDTOResp, NewsEntity, NewsRepository>
        implements NewsService {
    @Override
    protected NewsEntity dtoToEntity(NewsDTOReq newsDTOReq) {
        return DTOMapper.instance.newsReqToEntity((NewsDTOReqImpl) newsDTOReq);
    }

    @Override
    protected NewsDTOResp entityToDto(NewsEntity newsEntity) {
        return DTOMapper.instance.newsToDto((NewsModel) newsEntity);
    }

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.repo = newsRepository;
    }
}
