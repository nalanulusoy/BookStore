package com.app.bookstore.di

import com.app.bookstore.BuildConfig
import com.app.bookstore.feature.dashboard.data.BookListApiService
import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Nalan Ulusoy on 12,Mart,2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideBookListApiService(retrofit: Retrofit): BookListApiService {
        return retrofit.create(BookListApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideVolumeDetailApiService(retrofit: Retrofit): VolumeDetailApiService {
        return retrofit.create(VolumeDetailApiService::class.java)
    }
}