package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import com.app.bookstore.base.NavScreen
import com.app.bookstore.component.BookImageView
import com.app.bookstore.component.FavoriteButtonView
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.dashboard.data.response.VolumeInfo

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun BooksList(viewModel: DashboardViewModel, navController: NavController) {
    val lazyBookItems: LazyPagingItems<BookResult> = viewModel.books.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyBookItems) { books ->
            BookItem(books, viewModel, onClickStartSource = {
                navController.navigate(NavScreen.Detail.argDetailScreen + books?.id) {
                    popUpTo(NavScreen.Detail.argDetailScreen + books?.id) {
                        inclusive = true
                    }
                }
            }
            )
        }
        getLoadingState(lazyBookItems, this)
    }
}

fun getLoadingState(lazyMovieItems: LazyPagingItems<BookResult>, scope: LazyListScope) {
    lazyMovieItems.apply {
        scope.run {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
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
fun BookItem(book: BookResult?, viewModel: DashboardViewModel, onClickStartSource: () -> Unit) {
    book?.run {
        Card(
            modifier = Modifier
                .padding(15.dp)
                .clickable(onClick = onClickStartSource),
            elevation = 10.dp,
            shape = RoundedCornerShape(10)
        ) {
            Box() {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .fillMaxWidth()

                ) {
                    BookColumnView(volumeInfo = volumeInfo)
                }
                Surface(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(32.dp)
                        .align(Alignment.BottomEnd),
                ) {
                    val isChecked = findIsCheckedFavorite(book.id, viewModel)

                    FavoriteButtonView(
                        modifier = Modifier.padding(8.dp),
                        isChecked = isChecked
                    ) {
                        book.volumeInfo?.run {
                            viewModel.addFavorite(
                                BookData(
                                    publishedDate,
                                    title,
                                    imageLinks?.smallThumbnail,
                                    id.orEmpty()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

fun findIsCheckedFavorite(id: String?, viewModel: DashboardViewModel): Boolean {
    viewModel.favBooks.map { if (id == it.id.toString()) return true }
    return false
}


@Composable
fun BookColumnView(volumeInfo: VolumeInfo?) {
    BookTitle(
        volumeInfo?.title.orEmpty()
    )
    BookPublishedDate(
        volumeInfo?.publishedDate.orEmpty(),
    )
    BookImageView(
        volumeInfo?.imageLinks?.smallThumbnail.orEmpty()
    )
}

@Composable
fun BookTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        color = Color.DarkGray
    )
}

@Composable
fun BookPublishedDate(
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = subTitle,
        maxLines = 2,
        style = MaterialTheme.typography.subtitle1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold
    )
}