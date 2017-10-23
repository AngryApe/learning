package com.ape.dao;

import com.ape.entity.BaseEntity;

import java.util.List;

/**
 * AngryApe created at 2017-10-23
 */
public interface BaseDao {

    <T extends BaseEntity> T getById(Class<T> clazz, String id);

    <T extends BaseEntity> T save(T entity, String operator);

    <T extends BaseEntity> T update(BaseEntity entity, String operator);

    <T extends BaseEntity> int saveBatch(List<T> entities, String operator);

    <T extends BaseEntity> int updateBatch(List<T> entities, String operator);

    <T extends BaseEntity> boolean deleteEntity(T entity, String operator);

    <T extends BaseEntity> boolean deleteById(Class<T> clazz, String id, String operator);
}
