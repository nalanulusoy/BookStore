package com.app.bookstore.feature.search.data.repo

import com.app.bookstore.feature.search.datasource.BookSearchRemoteDataSource
import com.app.bookstore.feature.search.domain.BookSearchRepository


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class BookSearchDataRepository(private val remoteDataSource: BookSearchRemoteDataSource) :
    BookSearchRepository {
    override suspend fun getBookList(
        startIndex: Int,
        searchString: String,
        apiKey: String
    ) = remoteDataSource.getBookList(startIndex, searchString, apiKey)


}