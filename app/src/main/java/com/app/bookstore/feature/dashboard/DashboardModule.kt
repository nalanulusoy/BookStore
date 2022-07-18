package com.app.bookstore.feature.dashboard

import com.app.bookstore.db.BookDatabase
import com.app.bookstore.feature.dashboard.data.BookListApiService
import com.app.bookstore.feature.dashboard.data.datasource.BookDashboardLocalSource
import com.app.bookstore.feature.dashboard.data.datasource.BookDashboardRemoteSource
import com.app.bookstore.feature.dashboard.data.repo.BookDashboardDataRepository
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
@Module
@InstallIn(ViewModelComponent::class)
class DashboardModule {
    @Provides
    fun provideBookListApiService(retrofit: Retrofit): BookListApiService {
        return retrofit.create(BookListApiService::class.java)
    }


    @Provides
    @ViewModelScoped
    fun provideBookListRepository(
        localSource: BookDashboardLocalSource,
        remoteSource: BookDashboardRemoteSource
    ): BookDashboardRepository {
        return BookDashboardDataRepository(localSource, remoteSource)
    }

    @Provides
    @ViewModelScoped
    fun provideBookDashboardLocalSource(
        bookDatabase: BookDatabase
    ): BookDashboardLocalSource {
        return BookDashboardLocalSource(bookDatabase)
    }

    @Provides
    @ViewModelScoped
    fun provideBookDashboardRemoteSource(
        bookListApiService: BookListApiService
    ): BookDashboardRemoteSource {
        return BookDashboardRemoteSource(bookListApiService)
    }
}