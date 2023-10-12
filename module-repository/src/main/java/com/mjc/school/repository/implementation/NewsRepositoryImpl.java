package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Scope("singleton")
@Transactional
public class NewsRepositoryImpl extends AbstractBaseRepositoryImpl<NewsEntity>
        implements NewsRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public NewsEntity update(NewsEntity entity) {
        NewsEntity dbEntity = getEntityManager().find(getEntityClass(), entity.getId());
        entity.setCreateDate(dbEntity.getCreateDate());
        getEntityManager().merge(entity);
        return getEntityManager().find(getEntityClass(), dbEntity.getId());
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

    @Override
    @Transactional(readOnly = true)
    public List<NewsEntity> readAll() {
        var findAll = getEntityManager().createQuery("" +
                "SELECT a FROM " + getTableName() + " a " +
                "LEFT JOIN FETCH a.tags"
        );
        return findAll.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NewsEntity> readById(Long id) {
        var findOne = getEntityManager().createQuery("" +
                "SELECT a FROM " + getTableName() + " a " +
                "LEFT JOIN FETCH a.tags " +
                "WHERE a.id=:id"
        );
        findOne.setParameter("id", id);
        var findList = findOne.getResultList();
        if (findList.size() == 0)
            return Optional.empty();
        var result = (NewsEntity) findList.get(0);
        return Optional.ofNullable(result);
    }
}
