package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.AuthorEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Scope("singleton")
public class AuthorRepositoryImpl extends BaseRepositoryImpl<AuthorEntity>
        implements AuthorRepository {
    @Override
    public AuthorEntity create(AuthorEntity entity) {
        var id = generateID();
        var now = LocalDateTime.now();
        entity.setId(id);
        entity.setCreateDate(now);
        entity.setLastUpdateDate(now);
        allItems.add(entity);
        return entity;
    }

    @Override
    public AuthorEntity update(AuthorEntity entity) {
        var id = entity.getId();
        var entityToUpdate = this.readById(id);
        int index;
        if (entityToUpdate.isPresent() && allItems.contains(entityToUpdate.get()))
            index = allItems.indexOf(entityToUpdate.get());
        else
            throw new RuntimeException("found no author object with id: " + id);
        allItems.get(index).setName(entity.getName());
        var now = LocalDateTime.now();
        allItems.get(index).setLastUpdateDate(now);
        return allItems.get(index);
    }

    @Override
    public boolean deleteById(Long id) {
        allItems.removeIf(item -> item.getId().equals(id));
        dataSource.getAllNews().removeIf(item -> item.getAuthorId().equals(id));
        return true;
    }

    public AuthorRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        allItems = dataSource.getAllAuthors();
    }

}
