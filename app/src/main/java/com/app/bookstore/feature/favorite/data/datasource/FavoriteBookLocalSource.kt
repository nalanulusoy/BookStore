package com.app.bookstore.feature.favorite.data.datasource

import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class FavoriteBookLocalSource constructor(private val bookDatabase: BookDatabase) {

    suspend fun deleteBook(bookData: BookData) = bookDatabase.getDao().delete(bookData)
}