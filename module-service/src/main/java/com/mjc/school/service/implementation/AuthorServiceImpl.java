package com.mjc.school.service.implementation;

import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.service.dto.implementation.AuthorDTOReqImpl;
import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.AuthorEntity;
import com.mjc.school.repository.model.implementation.AuthorModel;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.DTOMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<AuthorDTOReq, AuthorDTOResp, AuthorEntity, AuthorRepository>
        implements AuthorService {
    @Override
    protected AuthorEntity dtoToEntity(AuthorDTOReq authorDTOReq) {
        return DTOMapper.instance.authorReqToEntity((AuthorDTOReqImpl) authorDTOReq);
    }

    @Override
    protected AuthorDTOResp entityToDto(AuthorEntity authorEntity) {
        return DTOMapper.instance.authorToAuthorDto((AuthorModel) authorEntity);
    }

     public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.repo = authorRepository;
    }
}
