package com.app.bookstore.feature.dashboard.domain

import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.data.response.BookListResponse
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
interface BookDashboardRepository {
    fun getFavoriteBooksData(): Flow<List<BookData>>

    suspend fun addFavoriteBook(bookData: BookData): Unit

    suspend fun getBookList(
        startIndex: Int,
        searchString: String,
        apiKey: String
    ): BookListResponse
}