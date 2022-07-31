package com.app.bookstore.feature.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import com.app.bookstore.base.NavScreen
import com.app.bookstore.component.SearchView
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.data.response.BookResult
import java.util.*

/**
 * Created by Nalan Ulusoy on 02,Temmuz,2022
 */
@Composable
fun SearchScreen(navController: NavController) {
    var state = remember { mutableStateOf(TextFieldValue("")) }

    val viewModel = hiltViewModel<SearchViewModel>()

    val lazyBookItems: LazyPagingItems<BookResult> = viewModel.books.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        SearchView(state = state)
        viewModel.searchStringKey(state.value.text)
        if(!state.value.text.isNullOrEmpty()){
            BookList( bookItems = lazyBookItems,navController)
        }
    }
}


@Composable
fun BookList( bookItems: LazyPagingItems<BookResult>,navController:NavController) {
    val books = bookItems.itemSnapshotList.items
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(books) { filteredBook ->
            BookListItem(
                bookText = filteredBook.volumeInfo?.title.orEmpty(),
                onItemClick = { selectedBoook ->
                    navController.navigate(NavScreen.Detail.argDetailScreen + filteredBook.id) {
                        popUpTo(NavScreen.Detail.argDetailScreen + filteredBook.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
            getLoadingState(bookItems,this)

    }
}
fun getLoadingState(lazyBookItems: LazyPagingItems<BookResult>, scope: LazyListScope) {
    lazyBookItems.apply {
        scope.run {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyBookItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyBookItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookListItem(bookText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(bookText) })
            .background(colorResource(id = R.color.white))
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = bookText, fontSize = 18.sp, color = Color.DarkGray, fontFamily = FontFamily.Monospace)
    }
}