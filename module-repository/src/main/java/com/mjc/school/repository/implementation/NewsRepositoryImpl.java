package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Scope("singleton")
public class NewsRepositoryImpl extends BaseRepositoryImpl<NewsEntity>
        implements NewsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private String tableName = "news";
    @Override
    public NewsEntity create(NewsEntity entity) {
        return null;
    }

    @Override
    public NewsEntity update(NewsEntity entity) {
        return null;
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
        return tableName;
    }
}
