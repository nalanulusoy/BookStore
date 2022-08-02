package com.app.bookstore.feature.detail

import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import com.app.bookstore.feature.detail.data.datasource.DetailRemoteDataSource
import com.app.bookstore.feature.detail.data.repo.VolumeDetailDataRepository
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
@InstallIn(ViewModelComponent::class)
@Module
object DetailModule {

    @Provides
    @ViewModelScoped
    fun provideDetailRemoteDataSource(
        apiService: VolumeDetailApiService
    ): DetailRemoteDataSource {
        return DetailRemoteDataSource(apiService)
    }

    @Provides
    @ViewModelScoped
    fun VolumeDetailRepository(
        detailRemoteDataSource: DetailRemoteDataSource
    ): VolumeDetailRepository {
        return VolumeDetailDataRepository(detailRemoteDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideVolumeDetailApiService(retrofit: Retrofit): VolumeDetailApiService {
        return retrofit.create(VolumeDetailApiService::class.java)
    }
}