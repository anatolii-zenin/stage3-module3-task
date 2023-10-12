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

    @Override
    public List<Resp> readAll() {
        return fetchAll();
    }

    @Override
    public Resp readById(Long id) {
        var item = getRepo().readById(id);
        if (item.isPresent())
            return entityToDto(getRepo().readById(id).get());
        else
            return null;
    }

    @Override
    public Resp create(@Validate Req createRequest) {
        return entityToDto(
                getRepo().create(dtoToEntity(createRequest))
        );
    }

    @Override
    public Resp update(@Validate Req updateRequest) {
        return entityToDto(
                getRepo().update(dtoToEntity(updateRequest))
        );
    }

    @Override
    public boolean deleteById(Long id) {
        return getRepo().deleteById(id);
    }

    protected List<Resp> fetchAll() {
        List<Entity> entities = getRepo().readAll();
        List<Resp> news = new ArrayList<>();

        for (var newsEntity : entities)
            news.add(entityToDto(newsEntity));

        return news;
    }

    protected abstract Entity dtoToEntity(Req dto);

    protected abstract Resp entityToDto(Entity model);
    
    protected abstract Repository getRepo();
}
