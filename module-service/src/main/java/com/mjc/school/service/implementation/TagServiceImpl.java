package com.mjc.school.service.implementation;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagDTOReq;
import com.mjc.school.service.dto.TagDTOResp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TagServiceImpl extends BaseServiceImpl<TagDTOReq, TagDTOResp, TagEntity, TagRepository>
        implements TagService {
    @Override
    protected TagEntity dtoToEntity(TagDTOReq dto) {
        return null;
    }

    @Override
    protected TagDTOResp entityToDto(TagEntity model) {
        return null;
    }
//    @Override
//    protected TagEntity dtoToEntity(TagDTOReq tagDTOReq) {
//        return mapper.tagReqToEntity(tagDTOReq);
//    }
//
//    @Override
//    protected TagDTOResp entityToDto(NewsEntity newsEntity) {
//        return mapper.tagToDto(newsEntity);
//    }
//
//    public NewsServiceImpl(TagRepository tagRepository) {
//        this.repo = tagRepository;
//    }
}
