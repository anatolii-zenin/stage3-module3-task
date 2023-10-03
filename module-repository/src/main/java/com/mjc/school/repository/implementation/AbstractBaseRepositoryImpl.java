package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public abstract class AbstractBaseRepositoryImpl<T extends BaseEntity<Long>> implements BaseRepository<T, Long> {
    @Override
    @Transactional(readOnly = true)
    public List<T> readAll() {
        var findAll = getEntityManager().createQuery("SELECT a FROM " + getTableName() + " a");
        return findAll.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> readById(Long id) {
        return Optional.ofNullable((T) getEntityManager().find(getEntityClass(), id));
    }

    @Override
    @Transactional
    public T create(T entity) {
        var mergedEntity = getEntityManager().merge(entity);
        return  (T) getEntityManager().find(getEntityClass(), mergedEntity.getId());
    }

    @Override
    @Transactional
    public abstract T update(T entity);

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        var obj = readById(id);
        if (obj.isPresent())
            getEntityManager().remove(obj.get());
        else
            return false;
        return readById(id).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        Optional<T> itemById = readById(id);
        return itemById.isPresent();
    }

    @Transactional
    public void insert(T entity) {
        getEntityManager().merge(entity);
    }

    protected abstract Class getEntityClass();
    protected abstract EntityManager getEntityManager();
    protected String getTableName() {
        var classStr = getEntityClass().toString();
        return classStr.split(" ")[1];
    }
}
