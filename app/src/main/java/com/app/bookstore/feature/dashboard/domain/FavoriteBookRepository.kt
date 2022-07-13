package com.app.bookstore.feature.dashboard.domain

import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase


/**
 * Created by Nalan Ulusoy on 13,Temmuz,2022
 */
class FavoriteBookRepository constructor(private val bookDatabase: BookDatabase) {

    suspend fun addFavoriteBook(params:Params){
        bookDatabase.getDao().insert(params.bookData)
    }

     fun getFavoriteBooksData() = bookDatabase.getDao().getFavoriteBooksData()

    data class Params(val bookData: BookData)
}