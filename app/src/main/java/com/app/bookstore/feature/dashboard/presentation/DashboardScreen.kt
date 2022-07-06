package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import com.app.bookstore.base.NavScreen
import com.app.bookstore.component.FavoriteButton
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.dashboard.data.response.VolumeInfo

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun BooksList(viewModel: DashboardViewModel,navController: NavController) {
    val lazyMovieItems: LazyPagingItems<BookResult> = viewModel.books.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyMovieItems) { books ->
            BookItem(books, onClickStartSource = {
                navController.navigate(NavScreen.Detail.argDetailScreen + books?.id) {
                    popUpTo(NavScreen.Detail.argDetailScreen + books?.id) {
                        inclusive = true
                    }
                }
            }
            )
        }
       getLoadingState(lazyMovieItems,this)
    }
}

fun getLoadingState(lazyMovieItems: LazyPagingItems<BookResult> ,scope: LazyListScope){
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
fun BookItem(book: BookResult?, onClickStartSource : () -> Unit) {
    book?.run {
        Card(
            modifier = Modifier
                .padding(15.dp)
                .clickable(onClick = onClickStartSource)
          ,
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth()

            ) {
             BookColumnView(volumeInfo = volumeInfo)
            }
        }
    }
}

@Composable
fun BookColumnView(volumeInfo: VolumeInfo?){
    BookTitle(
        volumeInfo?.title.orEmpty()
    )
    BookPublishedDate(
        volumeInfo?.publishedDate.orEmpty(),
    )
    BookImage(
        volumeInfo?.imageLinks?.smallThumbnail.orEmpty()
    )
    FavoriteButton(isChecked = false) {

    }
}

@Composable
fun BookImage(
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Book Review",
        error = painterResource(R.drawable.ic_broken_image),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Inside,
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