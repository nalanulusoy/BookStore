package com.app.bookstore.feature.search

import com.app.bookstore.base.extention.EMPTY
import com.app.bookstore.feature.search.data.BookSearchApiSource
import com.app.bookstore.feature.search.data.repo.BookSearchDataRepository
import com.app.bookstore.feature.search.datasource.BookSearchRemoteDataSource
import com.app.bookstore.feature.search.domain.BookSearchRepository
import com.app.bookstore.feature.search.domain.SearchBookSource
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
object SearchModule {
    @Provides
    @ViewModelScoped
    fun provideBookSearchRepository(
        remoteSource: BookSearchRemoteDataSource
    ): BookSearchRepository {
        return BookSearchDataRepository(remoteSource)
    }

    @Provides
    @ViewModelScoped
    fun provideBookSearchRemoteDataSource(
        apiSource: BookSearchApiSource
    ): BookSearchRemoteDataSource {
        return BookSearchRemoteDataSource(apiSource)
    }

    @Provides
    fun provideBookSearchApiSource(retrofit: Retrofit): BookSearchApiSource {
        return retrofit.create(BookSearchApiSource::class.java)
    }


    @Provides
    fun provideKeyString():String{
        return EMPTY
    }


    @Provides
    @ViewModelScoped
    fun provideSearchBookSource(repository:BookSearchRepository,keyString:String): SearchBookSource {
        return SearchBookSource(repository, keyString)
    }
}