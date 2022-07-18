package com.app.bookstore.di

import com.app.bookstore.db.BookDatabase
import com.app.bookstore.feature.dashboard.data.BookListApiService
import com.app.bookstore.feature.dashboard.domain.BookListRepository
import com.app.bookstore.feature.dashboard.domain.FavoriteBookRepository
import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import com.app.bookstore.feature.detail.data.repo.VolumeDetailDataRepository
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
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
    ): BookListRepository {
        return BookListRepository(apiService)
    }

    @Provides
    @ViewModelScoped
    fun provideFavoriteBookRepository(
        bookDatabase: BookDatabase
    ): FavoriteBookRepository {
        return FavoriteBookRepository(bookDatabase)
    }

}