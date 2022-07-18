package com.app.bookstore.feature.favorite.data

import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase
import com.app.bookstore.feature.favorite.data.datasource.FavoriteBookLocalSource
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class FavoriteBookDeleteDataRepository constructor(private val favoriteBookLocalSource: FavoriteBookLocalSource) :
    FavoriteBookDeleteRepository {

    override suspend fun favDeleteBook(bookData: BookData) =
        favoriteBookLocalSource.deleteBook(bookData)

}