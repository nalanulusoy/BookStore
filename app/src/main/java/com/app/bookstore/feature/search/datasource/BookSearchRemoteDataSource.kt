package com.app.bookstore.feature.search.datasource

import com.app.bookstore.feature.search.data.BookSearchApiSource


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class BookSearchRemoteDataSource constructor(private val apiSource: BookSearchApiSource) {

    suspend fun getBookList (startIndex: Int,
                             searchString: String,
                             apiKey: String) = apiSource.getBookList(startIndex, searchString, apiKey )
}