package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Scope("singleton")
@Transactional
public class NewsRepositoryImpl extends AbstractBaseRepositoryImpl<NewsEntity>
        implements NewsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public NewsEntity update(NewsEntity entity) {
        NewsEntity dbEntity = (NewsEntity) getEntityManager().find(getEntityClass(), entity.getId());
        entity.setCreateDate(dbEntity.getCreateDate());
        getEntityManager().merge(entity);
        return (NewsEntity) getEntityManager().find(getEntityClass(), dbEntity.getId());
    }

    @Override
    protected Class<NewsEntity> getEntityClass() {
        return NewsEntity.class;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected String getTableName() {
        return "news";
    }
}
