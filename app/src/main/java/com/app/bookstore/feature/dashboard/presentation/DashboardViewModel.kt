package com.app.bookstore.feature.dashboard.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import com.app.bookstore.feature.dashboard.domain.BookSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: BookDashboardRepository
) :
    ViewModel() {
    val books: Flow<PagingData<BookResult>> = Pager(PagingConfig(pageSize = 20)) {
        BookSource(repository)
    }.flow

    lateinit var favBooks: List<BookData>

    init {
        getFavBooks()
    }

    private fun getFavBooks() {
        viewModelScope.launch {
            try {
                repository.getFavoriteBooksData().collect { value ->
                    favBooks = value

                }
            } catch (e: Exception) {
                println("The flow has thrown an exception: $e")
            }
        }
    }

    fun addFavorite(bookData: BookData) {
        viewModelScope.launch {
            repository.addFavoriteBook(bookData)
        }
    }


}