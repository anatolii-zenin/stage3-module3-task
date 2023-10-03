package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.dataloader.DataLoader;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Scope("singleton")
public class AuthorRepositoryImpl extends AbstractBaseRepositoryImpl<AuthorEntity>
        implements AuthorRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public AuthorEntity update(AuthorEntity entity) {
        AuthorEntity dbEntity = (AuthorEntity) getEntityManager().find(getEntityClass(), entity.getId());
        dbEntity.setName(entity.getName());
        return (AuthorEntity) getEntityManager().find(getEntityClass(), dbEntity.getId());
    }

    @Override
    protected Class getEntityClass() {
        return AuthorEntity.class;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
