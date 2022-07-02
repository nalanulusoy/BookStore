package com.app.bookstore.di

import com.app.bookstore.feature.dashboard.data.BookListApiService
import com.app.bookstore.feature.dashboard.domain.BookListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * Created by Nalan Ulusoy on 02,Temmuz,2022
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideBookListRepository(
        apiService: BookListApiService
    ) : BookListRepository{
        return BookListRepository(apiService)
    }
}