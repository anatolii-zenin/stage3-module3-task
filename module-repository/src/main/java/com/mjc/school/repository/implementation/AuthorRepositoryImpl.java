package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.AuthorEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AuthorRepositoryImpl
        implements AuthorRepository {
    @PersistenceContext
    EntityManager entityManager;

    private String tableName = "author";

    @Override
    public List<AuthorEntity> readAll() {
        var findAll = getEntityManager().createQuery("SELECT a FROM author a", AuthorEntity.class);
        return findAll.getResultList();
    }

    @Override
    public Optional<AuthorEntity> readById(Long id) {
        return Optional.empty();
    }

    @Override

    public AuthorEntity create(AuthorEntity entity) {
        getEntityManager().merge(entity);
        return entityManager.find(AuthorEntity.class, entity.getId());
    }

    @Override
    public AuthorEntity update(AuthorEntity entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    protected Class<AuthorEntity> getEntityClass() {
        return AuthorEntity.class;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected String getTableName() {
        return tableName;
    }
}
