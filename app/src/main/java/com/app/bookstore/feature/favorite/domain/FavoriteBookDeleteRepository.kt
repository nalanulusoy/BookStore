package com.app.bookstore.feature.favorite.domain

import com.app.bookstore.db.BookData

/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
interface FavoriteBookDeleteRepository {
    suspend fun favDeleteBook(bookData: BookData): Unit
}