package com.app.bookstore.feature.dashboard.data.datasource

import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class BookDashboardLocalSource constructor(private val bookDatabase: BookDatabase) {

    suspend fun addFavoriteBook(bookData: BookData){
        bookDatabase.getDao().insert(bookData)
    }

    fun getFavoriteBooksData() = bookDatabase.getDao().getFavoriteBooksData()

}