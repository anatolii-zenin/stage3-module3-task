package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseEntity<Long>> implements BaseRepository<T, Long> {
    @Override
    public List<T> readAll() {
        var findAll = getEntityManager().createQuery("SELECT * FROM " + getTableName());
        return findAll.getResultList();
    }

    @Override
    public Optional<T> readById(Long id) {
        return Optional.ofNullable(getEntityManager().find(getEntityClass(), id));
    }

    @Override
    public abstract T create(T entity);

    @Override
    public abstract T update(T entity);

    @Override
    public boolean deleteById(Long id) {
        var obj = readById(id);
        if (obj.isPresent())
            getEntityManager().remove(obj);
        else
            return false;
        return !readById(id).isPresent();
    }

    @Override
    public boolean existById(Long id) {
        Optional<T> itemById = readById(id);
        return itemById.isPresent();
    }

    public void insert(T entity) {
        getEntityManager().merge(entity);
    }

    protected abstract Class<T> getEntityClass();
    protected abstract EntityManager getEntityManager();
    protected abstract String getTableName();
}
