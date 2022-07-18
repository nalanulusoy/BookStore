package com.app.bookstore.feature.dashboard.data.repo

import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.data.datasource.BookDashboardLocalSource
import com.app.bookstore.feature.dashboard.data.datasource.BookDashboardRemoteSource
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class BookDashboardDataRepository constructor(
    private val localSource: BookDashboardLocalSource,
    private val remoteSource: BookDashboardRemoteSource
) : BookDashboardRepository {

    override fun getFavoriteBooksData() = localSource.getFavoriteBooksData()

    override suspend fun addFavoriteBook(bookData: BookData) = localSource.addFavoriteBook(bookData)

    override suspend fun getBookList(
        startIndex: Int,
        searchString: String,
        apiKey: String
    ) = remoteSource.getBookList(startIndex, searchString, apiKey)
}
