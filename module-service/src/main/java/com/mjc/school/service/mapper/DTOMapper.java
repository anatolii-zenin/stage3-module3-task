package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.service.dto.implementation.AuthorDTORespImpl;
import com.mjc.school.service.dto.implementation.NewsDTOReqImpl;
import com.mjc.school.service.dto.implementation.NewsDTORespImpl;
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
    public abstract NewsDTORespImpl newsToDto(NewsEntity newsEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    @Mapping(target = "author", expression = "java(getAuthorEntityById(newsDTOReq.getAuthorId()))")
    public abstract NewsEntity newsReqToEntity(NewsDTOReqImpl newsDTOReq);
    public abstract AuthorDTORespImpl authorToAuthorDto(AuthorEntity authorEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    public abstract AuthorEntity authorReqToEntity(AuthorDTOReqImpl authorDTOReq);

    abstract AuthorEntity authorRespToEntity(AuthorDTORespImpl authorDTOResp);
    public AuthorEntity getAuthorEntityById(Long id) {
        return authorRespToEntity((AuthorDTORespImpl) authorService.readById(id));
    }
}