package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import com.app.bookstore.base.NavScreen
import com.app.bookstore.feature.dashboard.data.BookResult
import com.app.bookstore.feature.favorite.FavoriteScreen
import com.app.bookstore.feature.search.SearchScreen

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun DashboardScreen(viewModel: DashboardViewModel){
      val  navController = rememberNavController()
        val items = listOf(
            NavScreen.Dashboard,
            NavScreen.Favorite,
            NavScreen.Search
        )
        Scaffold(
            topBar =  { AppBar() },
            bottomBar = { BottomBar(navController = navController, items = items) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(bottom = innerPadding.calculateBottomPadding())
            )
            { BooksList(viewModel) }

            NavHost(navController, startDestination = NavScreen.Dashboard.route, Modifier.padding(innerPadding)) {
                composable(NavScreen.Dashboard.route) {
                }
                composable(NavScreen.Favorite.route){
                    FavoriteScreen()
                }
                composable(NavScreen.Search.route){
                   SearchScreen()
                }
            }
        }
    }
@Composable
fun BooksList(viewModel: DashboardViewModel) {
    val lazyMovieItems: LazyPagingItems<BookResult> = viewModel.books.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyMovieItems) { books ->
            BookItem(books)
        }
        lazyMovieItems.apply {
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
fun BookItem(book: BookResult?) {
    book?.run {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BookTitle(
                volumeInfo?.title.orEmpty(),
                modifier = Modifier.weight(1f)
            )
            BookImage(
                volumeInfo?.imageLinks?.smallThumbnail.orEmpty(),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun BookImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("")
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_broken_image),
        contentDescription = stringResource(R.string.home_title),
        contentScale = ContentScale.Fit,
        modifier = modifier
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
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BottomBar(navController:NavController,items:List<NavScreen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.bottomBarData?.icon?.let { Icon(it, contentDescription = null) } },
                label = { screen.bottomBarData?.title?.let {Text( stringResource(it)) }},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun AppBar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = colorResource(id = R.color.purple_500),
        modifier = Modifier.height(60.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.app_name),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}