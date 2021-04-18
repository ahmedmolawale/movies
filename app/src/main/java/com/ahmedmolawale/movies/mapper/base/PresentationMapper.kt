package com.ahmedmolawale.movies.mapper.base

interface PresentationMapper<Domain, Presentation> {

    fun mapToPresentation(domain: Domain): Presentation
    fun mapToDomain(presentation: Presentation): Domain

    fun mapToDomainList(presentation: List<Presentation>): List<Domain> {
        return presentation.mapTo(mutableListOf(), ::mapToDomain)
    }

    fun mapToPresentationList(domain: List<Domain>): List<Presentation> {
        return domain.mapTo(mutableListOf(), ::mapToPresentation)
    }
}
