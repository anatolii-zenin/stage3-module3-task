package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Scope("singleton")
public class NewsRepositoryImpl extends BaseRepositoryImpl<NewsEntity>
        implements NewsRepository {
    @Override
    public NewsEntity create(NewsEntity entity) {
        var id = generateID();
        var now = LocalDateTime.now();
        entity.setId(id);
        entity.setCreateDate(now);
        entity.setLastUpdateDate(now);
        allItems.add(entity);
        return entity;
    }

    @Override
    public NewsEntity update(NewsEntity entity) {
        var id = entity.getId();
        int index;
        if (allItems.contains(entity))
            index = allItems.indexOf(entity);
        else
            throw new RuntimeException("found no news object with id: " + id);
        allItems.get(index).setContent(entity.getContent());
        allItems.get(index).setTitle(entity.getTitle());
        allItems.get(index).setAuthorId(entity.getAuthorId());
        var now = LocalDateTime.now();
        allItems.get(index).setLastUpdateDate(now);
        return allItems.get(index);
    }

    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        allItems = dataSource.getAllNews();
    }
}
