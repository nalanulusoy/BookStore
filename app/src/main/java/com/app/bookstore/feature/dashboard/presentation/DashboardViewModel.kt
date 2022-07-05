package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.bookstore.base.BaseViewModel
import com.app.bookstore.feature.dashboard.data.BookListResponse
import com.app.bookstore.feature.dashboard.data.BookResult
import com.app.bookstore.feature.dashboard.domain.BookListRepository
import com.app.bookstore.feature.dashboard.domain.BookSource
import com.app.bookstore.feature.detail.presentation.DetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(private val bookListRepository: BookListRepository) : BaseViewModel () {
    val books: Flow<PagingData<BookResult>> = Pager(PagingConfig(pageSize = 20)) {
        BookSource(bookListRepository)
    }.flow

}