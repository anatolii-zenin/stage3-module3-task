package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.validator.Validate;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<Req, Resp, Entity extends BaseEntity<Long>,
        Repository extends BaseRepository<Entity, Long>>
        implements BaseService<Req, Resp, Long> {
    protected Repository repo;
    @Override
    public List<Resp> readAll() {
        return fetchAll();
    }

    @Override
    public Resp readById(Long id) {
        var item = repo.readById(id);
        if (item.isPresent())
            return entityToDto(repo.readById(id).get());
        else
            return null;
    }

    @Override
    public Resp create(@Validate Req createRequest) {
        return entityToDto(
                repo.create(dtoToEntity(createRequest))
        );
    }

    @Override
    public Resp update(@Validate Req updateRequest) {
        return entityToDto(
                repo.update(dtoToEntity(updateRequest))
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return repo.deleteById(id);
    }

    protected List<Resp> fetchAll() {
        List<Entity> entities = repo.readAll();
        List<Resp> news = new ArrayList<>();

        for (var newsEntity : entities)
            news.add(entityToDto(newsEntity));

        return news;
    }

    protected abstract Entity dtoToEntity(Req dto);

    protected abstract Resp entityToDto(Entity model);
}
