package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.BaseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseEntity<Long>> implements BaseRepository<T, Long> {
    protected DataSource dataSource;
    protected List<T> allItems;
    @Override
    public List<T> readAll() {
        return allItems;
    }

    @Override
    public Optional<T> readById(Long id) {
        return allItems.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    @Override
    public abstract T create(T entity);

    @Override
    public abstract T update(T entity);

    @Override
    public boolean deleteById(Long id) {
        int index;
        Optional<T> itemToDelete = readById(id);
        if (itemToDelete.isPresent() && allItems.contains(itemToDelete.get()))
            index = allItems.indexOf(itemToDelete.get());
        else
            throw new RuntimeException("found no news object with id: " + id);
        allItems.remove(index);
        return true;
    }

    @Override
    public boolean existById(Long id) {
        Optional<T> itemById = readById(id);
        return itemById.isPresent();
    }

    protected Long generateID() {
        Long largestId = allItems.stream()
                .max(Comparator.comparingLong(BaseEntity::getId)).get().getId();
        return ++largestId;
    }
}
