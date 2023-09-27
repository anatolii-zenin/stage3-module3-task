package com.mjc.school.service;

import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.service.dto.implementation.AuthorDTORespImpl;
import com.mjc.school.service.dto.implementation.NewsDTOReqImpl;
import com.mjc.school.service.dto.implementation.NewsDTORespImpl;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.repository.model.implementation.NewsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper instance = Mappers.getMapper(DTOMapper.class);
    NewsDTORespImpl newsToDto(NewsModel newsEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    NewsModel newsReqToEntity(NewsDTOReqImpl newsDTOResp);
    AuthorDTORespImpl authorToAuthorDto(AuthorModel authorEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    AuthorModel authorReqToEntity(AuthorDTOReqImpl authorDTOResp);
}