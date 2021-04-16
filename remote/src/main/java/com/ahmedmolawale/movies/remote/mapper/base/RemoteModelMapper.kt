package com.ahmedmolawale.movies.remote.mapper.base

interface RemoteModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

    fun mapFromModelList(models: List<M>): List<E> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}