package com.ahmedmolawale.movies.data.mapper.base

interface EntityMapper<E, D> {

    fun mapToDomain(entity: E): D

    fun mapToEntity(domain: D): E

    fun mapToDomainList(entities: List<E>): List<D> {
        return entities.mapTo(mutableListOf(), ::mapToDomain)
    }

    fun mapToEntityList(domainModels: List<D>): List<E> {
        return domainModels.mapTo(mutableListOf(), ::mapToEntity)
    }
}
