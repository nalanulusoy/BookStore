package com.app.bookstore.feature.dashboard.domain

import com.app.bookstore.feature.dashboard.data.BookListApiService

/**
 * Created by Nalan Ulusoy on 02,Temmuz,2022
 */
class BookListRepository constructor(private val apiService: BookListApiService) {

    suspend fun getBookList(params: Params) = params.run {
        apiService.getBookList(startIndex, searchString, apiKey)
    }

    data class Params(
        val startIndex: Int,
        val searchString: String,
        val apiKey: String
    )
}