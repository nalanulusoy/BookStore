package com.app.bookstore.feature.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.presentation.DashboardViewModel
import com.app.bookstore.feature.favorite.presentation.FavoriteViewModel
import swipeCardItem

/**
 * Created by Nalan Ulusoy on 02,Temmuz,2022
 */

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(dashboardViewModel: DashboardViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        swipeCardList(dashboardViewModel.favBooks)
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun swipeCardList(books: List<BookData>) {
    val favoriteViewModel = hiltViewModel() as FavoriteViewModel
    val listComparator = Comparator<BookData> { left, right ->
        left.id.compareTo(right.id)
    }
    var list by remember { mutableStateOf(listOf<BookData>()) }
    list = books

    val comparator by remember { mutableStateOf(listComparator) }

    LazyColumn {
        val sortedList = list.sortedWith(comparator)

        items(sortedList, key = { it.id }) { item ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                list = list.toMutableList().also {
                    it.remove(item)
                    favoriteViewModel.deleteFavBook(item)
                }
            }
            swipeCardItem(dismissState,item)
        }
    }
}

