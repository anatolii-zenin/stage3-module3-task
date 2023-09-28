package com.mjc.school.service;

import com.mjc.school.repository.model.implementation.AuthorEntityImpl;
import com.mjc.school.repository.model.implementation.NewsEntityImpl;
import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.service.dto.implementation.AuthorDTORespImpl;
import com.mjc.school.service.dto.implementation.NewsDTOReqImpl;
import com.mjc.school.service.dto.implementation.NewsDTORespImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper instance = Mappers.getMapper(DTOMapper.class);
    NewsDTORespImpl newsToDto(NewsEntityImpl newsEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    NewsEntityImpl newsReqToEntity(NewsDTOReqImpl newsDTOResp);
    AuthorDTORespImpl authorToAuthorDto(AuthorEntityImpl authorEntity);
    @Mapping(ignore = true, target = "createDate")
    @Mapping(ignore = true, target = "lastUpdateDate")
    AuthorEntityImpl authorReqToEntity(AuthorDTOReqImpl authorDTOResp);
}