package com.ahmedmolawale.movies.cache.mapper.base

interface CacheModelMapper<Model, Entity> {

    fun mapToModel(entity: Entity): Model
    fun mapToEntity(model: Model): Entity

    fun mapToEntityList(models: List<Model>): List<Entity> {
        return models.mapTo(mutableListOf(), ::mapToEntity)
    }

    fun mapToModelList(entities: List<Entity>): List<Model> {
        return entities.mapTo(mutableListOf(), ::mapToModel)
    }
}
