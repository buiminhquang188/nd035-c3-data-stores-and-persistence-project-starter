package com.udacity.jdnd.course3.critter.common.mapper;

import java.util.List;

public interface BaseMapper<T, D> {
    List<D> entitiesToDTOs(List<T> entities);

    D entityToDTO(T entity);
}
