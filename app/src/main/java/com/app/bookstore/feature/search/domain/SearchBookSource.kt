package com.app.bookstore.feature.search.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.bookstore.base.extention.GOOGLE_API_KEY
import com.app.bookstore.feature.dashboard.data.response.BookResult
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class SearchBookSource   constructor(val repository: BookSearchRepository,  var keyString: String):
    PagingSource<Int, BookResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookResult> {
        return try {
            val nextPage = params.key ?: 1

            val response = if(!keyString.isNullOrEmpty())repository.getBookList(
                nextPage,
                keyString,
                GOOGLE_API_KEY
            ) else null

            LoadResult.Page(
                data = response?.items ?: emptyList(),
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