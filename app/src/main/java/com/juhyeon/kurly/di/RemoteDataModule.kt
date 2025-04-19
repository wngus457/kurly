package com.juhyeon.kurly.di

import com.juhyeon.kurly.shared.data.feature.home.HomeRemoteDataSource
import com.juhyeon.kurly.shared.remote.feature.home.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {

    @Singleton
    @Binds
    abstract fun bindHomeRemoteDataSource(source: HomeRemoteDataSourceImpl): HomeRemoteDataSource
}