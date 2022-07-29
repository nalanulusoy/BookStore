package com.app.bookstore.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.search.domain.BookSearchRepository
import com.app.bookstore.feature.search.domain.SearchBookSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
@HiltViewModel
class SearchViewModel @Inject constructor(repository: BookSearchRepository) : ViewModel() {
    private val searchKey: MutableStateFlow<String> = MutableStateFlow("")

    val books: Flow<PagingData<BookResult>> = searchKey.flatMapLatest {
        Pager(PagingConfig(pageSize = 20)) {
            SearchBookSource(repository, it)
        }.flow
    }

    fun searchStringKey(searchStringKey: String) {
        searchKey.tryEmit(searchStringKey)
    }
}