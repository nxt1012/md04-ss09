package com.ra.lecture.md04ss09.model.service;

import java.util.List;

public interface IGenericService<Entity, Id> {
    List<Entity> getAll();

    boolean save(Entity entity);

    Entity getById(Id id);

    boolean update(Entity entity, Id id);

    boolean delete(Id id);
}
