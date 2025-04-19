package com.juhyeon.kurly.di

import com.juhyeon.kurly.shared.data.feature.home.HomeRepositoryImpl
import com.juhyeon.kurly.shared.domain.feature.home.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository
}