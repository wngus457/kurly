package com.juhyeon.kurly.shared.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface DataMapper<Domain, Data> {

    fun toDomain(data: Data): Domain
    fun toDomains(data: List<Data>): List<Domain> = data.map(::toDomain)

    fun toData(domain: Domain): Data
    fun toDataList(domains: List<Domain>): List<Data> = domains.map(::toData)

    fun toDomain(data: Flow<Data>): Flow<Domain> = data.map(::toDomain)
    fun toDomains(data: Flow<List<Data>>): Flow<List<Domain>> = data.map(::toDomains)

    fun toData(domain: Flow<Domain>): Flow<Data> = domain.map(::toData)
    fun toDataList(domain: Flow<List<Domain>>): Flow<List<Data>> = domain.map(::toDataList)
}