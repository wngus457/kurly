package com.juhyeon.kurly.di

import com.juhyeon.kurly.shared.util.android.LogHelper
import com.juhyeon.kurly.shared.util.android.LogHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilModule {

    @Singleton
    @Binds
    abstract fun provideLogHelper(logHelper: LogHelperImpl): LogHelper
}