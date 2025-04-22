package com.juhyeon.kurly.di

import android.content.Context
import com.juhyeon.kurly.shared.data.feature.home.HomeLocalDataSource
import com.juhyeon.kurly.shared.local.db.AppDestructibleDatabase
import com.juhyeon.kurly.shared.local.home.HomeLocalDataSourceImpl
import com.juhyeon.kurly.shared.local.product.ProductBookmarkDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {

    @Singleton
    @Binds
    abstract fun bindHomeLocalDataSource(impl: HomeLocalDataSourceImpl): HomeLocalDataSource

    companion object {

        @Singleton
        @Provides
        fun provideAppDestructibleDatabase(@ApplicationContext context: Context): AppDestructibleDatabase = AppDestructibleDatabase.buildDatabase(context)

        @Singleton
        @Provides
        fun provideProductBookmarkDao(appDatabase: AppDestructibleDatabase): ProductBookmarkDao = appDatabase.productBookmarkDao()
    }
}