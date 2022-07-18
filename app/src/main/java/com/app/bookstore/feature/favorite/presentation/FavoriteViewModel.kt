package com.app.bookstore.feature.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Nalan Ulusoy on 14,Temmuz,2022
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteBookDeleteRepository): ViewModel(){

    fun deleteFavBook(bookData: BookData){
        viewModelScope.launch {
            repository.favDeleteBook(bookData)
        }
    }
}