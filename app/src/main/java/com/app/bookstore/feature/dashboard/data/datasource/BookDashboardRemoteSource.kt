package com.app.bookstore.feature.dashboard.data.datasource

import com.app.bookstore.feature.dashboard.data.BookListApiService


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class BookDashboardRemoteSource constructor(private val apiService: BookListApiService) {

    suspend fun getBookList(
        startIndex: Int,
        searchString: String,
        apiKey: String
    ) = apiService.getBookList(startIndex, searchString, apiKey)


}