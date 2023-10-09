package com.mjc.school.repository.implementation;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.implementation.TagEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TagRepositoryImpl extends AbstractBaseRepositoryImpl<TagEntity> implements TagRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public TagEntity update(TagEntity entity) {
        TagEntity dbEntity = getEntityManager().find(getEntityClass(), entity.getId());
        entity.setCreateDate(dbEntity.getCreateDate());
        getEntityManager().merge(entity);
        return getEntityManager().find(getEntityClass(), dbEntity.getId());
    }

    @Override
    protected Class<TagEntity> getEntityClass() {
        return TagEntity.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected String getTableName() {
        return "tag";
    }
}
