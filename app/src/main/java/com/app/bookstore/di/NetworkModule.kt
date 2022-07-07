package com.app.bookstore.di

import com.app.bookstore.BuildConfig
import com.app.bookstore.base.networkstate.FlowCallAdapterFactory
import com.app.bookstore.feature.dashboard.data.BookListApiService
import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Nalan Ulusoy on 12,Mart,2022
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

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
    fun provideConverterFactory() : MoshiConverterFactory= MoshiConverterFactory.create(moshi)


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
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