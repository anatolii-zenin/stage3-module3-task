package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Scope("singleton")
public class AuthorRepositoryImpl extends AbstractBaseRepositoryImpl<AuthorEntity>
        implements AuthorRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public AuthorEntity update(AuthorEntity entity) {
        AuthorEntity dbEntity = getEntityManager().find(getEntityClass(), entity.getId());
        entity.setCreateDate(dbEntity.getCreateDate());
        getEntityManager().merge(entity);
        return getEntityManager().find(getEntityClass(), dbEntity.getId());
    }

    @Override
    protected Class<AuthorEntity> getEntityClass() {
        return AuthorEntity.class;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected String getTableName() {
        return "author";
    }
}
