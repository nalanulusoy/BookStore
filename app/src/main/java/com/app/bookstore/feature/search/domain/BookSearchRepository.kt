package com.app.bookstore.feature.search.domain

import com.app.bookstore.base.extention.GOOGLE_API_KEY
import com.app.bookstore.feature.dashboard.data.response.BookListResponse


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
interface BookSearchRepository {
    suspend fun getBookList(
        startIndex: Int,
        searchString: String,
        apiKey: String = GOOGLE_API_KEY
    ): BookListResponse
}