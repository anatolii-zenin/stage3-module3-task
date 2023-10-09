package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.service.dto.AuthorDTOReq;
import com.mjc.school.service.dto.AuthorDTOResp;
import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.service.AuthorService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class AuthorServiceImpl extends BaseServiceImpl<AuthorDTOReq, AuthorDTOResp, AuthorEntity, AuthorRepository>
        implements AuthorService {
    @Override
    protected AuthorEntity dtoToEntity(AuthorDTOReq authorDTOReq) {
        return mapper.authorReqToEntity(authorDTOReq);
    }

    @Override
    protected AuthorDTOResp entityToDto(AuthorEntity authorEntity) {
        return mapper.authorToAuthorDto(authorEntity);
    }

     public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.repo = authorRepository;
    }
}
