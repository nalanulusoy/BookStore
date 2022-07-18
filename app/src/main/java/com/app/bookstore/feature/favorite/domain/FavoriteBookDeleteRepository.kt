package com.app.bookstore.feature.favorite.domain

import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class FavoriteBookDeleteRepository constructor (private val bookDatabase: BookDatabase) {

   suspend fun favDeleteBook(params: Params){
        bookDatabase.getDao().delete(params.bookData)
    }

    data class Params(val bookData: BookData)
}