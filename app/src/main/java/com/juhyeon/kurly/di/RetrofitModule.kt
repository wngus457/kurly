package com.juhyeon.kurly.di

import android.content.Context
import com.juhyeon.kurly.BuildConfig
import com.juhyeon.kurly.shared.util.android.LogHelper
import com.kurly.android.mockserver.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(logHelper: LogHelper, context: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            )
            .addInterceptor(MockInterceptor(context))
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                logHelper.log("RequestHeader: ${request.headers.joinToString()}")
                logHelper.log("ResponseBody: ${response.peekBody(Long.MAX_VALUE).string()}")

                response
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}