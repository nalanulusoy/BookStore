package com.app.bookstore.feature.dashboard.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.bookstore.feature.dashboard.data.response.BookResult


/**
 * Created by Nalan Ulusoy on 02,Temmuz,2022
 */
class BookSource (private val repository: BookListRepository): PagingSource<Int, BookResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookResult> {
        return try {
            val nextPage = params.key ?: 1
            val response  = repository.getBookList(BookListRepository.Params(nextPage,"programing","AIzaSyBFjF3etM9cLDeIVAxq1LiN-dIPyfbK3w0"))

            LoadResult.Page(
                data = response.items,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BookResult>): Int? {
        TODO("Not yet implemented")
    }
}