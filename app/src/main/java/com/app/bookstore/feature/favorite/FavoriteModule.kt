package com.app.bookstore.feature.favorite

import com.app.bookstore.db.BookDatabase
import com.app.bookstore.feature.favorite.data.FavoriteBookDeleteDataRepository
import com.app.bookstore.feature.favorite.data.datasource.FavoriteBookLocalSource
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
@InstallIn(ViewModelComponent::class)
@Module
 object FavoriteModule {

    @Provides
    @ViewModelScoped
    fun provideFavoriteBookDeleteRepository(
        favoriteBookLocalSource: FavoriteBookLocalSource
    ): FavoriteBookDeleteRepository {
        return FavoriteBookDeleteDataRepository(favoriteBookLocalSource)
    }


    @Provides
    @ViewModelScoped
    fun provideFavoriteBookLocalSource(
        bookDatabase: BookDatabase
    ): FavoriteBookLocalSource {
        return FavoriteBookLocalSource(bookDatabase)
    }

}