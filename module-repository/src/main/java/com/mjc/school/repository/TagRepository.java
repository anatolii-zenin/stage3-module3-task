package com.mjc.school.repository;

import com.mjc.school.repository.model.implementation.TagEntity;

import java.util.List;

public interface TagRepository extends BaseRepository<TagEntity, Long> {
    List<TagEntity> readByNewsId(Long newsId);
}
