package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.NewsDTOReq;
import com.mjc.school.service.dto.NewsDTOResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class DTOMapper {
    @Autowired
    protected AuthorService authorService;
    @Mapping(target = "authorId", expression = "java(newsEntity.getAuthor().getId())")
    public abstract NewsDTOResp newsToDto(NewsEntity newsEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(target = "author", expression = "java(getAuthorEntityById(newsDTOReq.getAuthorId()))")
    public abstract NewsEntity newsReqToEntity(NewsDTOReq newsDTOReq);
    public abstract AuthorDTOResp authorToAuthorDto(AuthorEntity authorEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    public abstract AuthorEntity authorReqToEntity(AuthorDTOReq authorDTOReq);

    abstract AuthorEntity authorRespToEntity(AuthorDTOResp authorDTOResp);
    public AuthorEntity getAuthorEntityById(Long id) {
        return authorRespToEntity(authorService.readById(id));
    }
}