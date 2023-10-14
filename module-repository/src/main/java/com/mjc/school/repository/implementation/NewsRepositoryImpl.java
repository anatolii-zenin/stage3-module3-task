package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<NewsEntity> readAll() {
        var findAll = getEntityManager().createQuery("" +
                "SELECT DISTINCT a FROM " + getTableName() + " a " +
                "LEFT JOIN FETCH a.tags", NewsEntity.class
        );
        return findAll.getResultList();
    }

    @Override
    public Optional<NewsEntity> readById(Long id) {
        var findOne = getEntityManager().createQuery("" +
                "SELECT DISTINCT a FROM " + getTableName() + " a " +
                "LEFT JOIN FETCH a.tags " +
                "WHERE a.id=:id", NewsEntity.class
        );
        findOne.setParameter("id", id);
        var findList = findOne.getResultList();
        if (findList.size() == 0)
            return Optional.empty();
        var result = (NewsEntity) findList.get(0);
        return Optional.ofNullable(result);
    }

    @Override
    public List<NewsEntity> readNewsByCriteria(NewsEntity req) {
        //TODO: refactor
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> news = criteriaQuery.from(NewsEntity.class);
        var newsAuthors = news.join("author", JoinType.LEFT);
        var newsTags = news.join("tags", JoinType.LEFT);

        EntityGraph<NewsEntity> fetchGraph = getEntityManager().createEntityGraph(NewsEntity.class);
        fetchGraph.addSubgraph("author");
        fetchGraph.addSubgraph("tags");

        var predicates = new ArrayList<Predicate>();

        var authorName = req.getAuthor().getName();
        var authorId = req.getAuthor().getId();

        if (authorName != null || authorId != null) {
            if (authorName != null) {
                predicates.add(criteriaBuilder.equal(newsAuthors.get("name"), authorName));
            } else {
                predicates.add(criteriaBuilder.equal(newsAuthors.get("id"), authorId));
            }
        }

        var newsTitle = req.getTitle();
        var newsContent = req.getContent();

        if (newsTitle != null)
            predicates.add(criteriaBuilder.like(news.get("title"), "%" + newsTitle + "%"));
        if (newsContent != null)
            predicates.add(criteriaBuilder.like(news.get("content"), "%" + newsContent + "%"));

        var tags = req.getTags();
        if (tags.size() > 0) {
            for (var tag : tags) {
                if (tag.getName() != null)
                    predicates.add(criteriaBuilder.equal(newsTags.get("name"), tag.getName()));
                if (tag.getId() != null)
                    predicates.add(criteriaBuilder.equal(newsTags.get("id"), tag.getId()));
            }
        }

        criteriaQuery.select(news).where(predicates.toArray(Predicate[]::new)).distinct(true);

        return entityManager
                .createQuery(criteriaQuery)
                .setHint("javax.persistence.loadgraph", fetchGraph)
                .getResultList();
    }
}
