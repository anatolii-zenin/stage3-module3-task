package com.mjc.school.repository.implementation;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.implementation.TagEntity;

import javax.persistence.EntityManager;

public class TagRepositoryImpl extends AbstractBaseRepositoryImpl<TagEntity> implements TagRepository {
    @Override
    public TagEntity update(TagEntity entity) {
        return null;
    }

    @Override
    protected Class getEntityClass() {
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return null;
    }

    @Override
    protected String getTableName() {
        return "tag";
    }
}
