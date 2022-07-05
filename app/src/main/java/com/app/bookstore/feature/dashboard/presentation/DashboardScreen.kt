package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.app.bookstore.R
import com.app.bookstore.base.ErrorItem
import com.app.bookstore.base.LoadingItem
import com.app.bookstore.base.LoadingView
import com.app.bookstore.base.NavScreen
import com.app.bookstore.feature.dashboard.data.BookResult
import com.app.bookstore.feature.dashboard.data.VolumeInfo
import com.app.bookstore.feature.favorite.FavoriteScreen
import com.app.bookstore.feature.search.SearchScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.app.bookstore.feature.detail.presentation.DetailScreen as DetailScreen

/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun DashboardScreen(viewModel: DashboardViewModel){
      val  navController = rememberNavController()
    ProvideWindowInsets {
        NavHost(
            navController,
            startDestination = NavScreen.Dashboard.route,
            Modifier.padding()
        ) {
            composable(NavScreen.Dashboard.route) {
             MainScreen(navController = navController, viewModel =viewModel , route = NavScreen.Dashboard.route)
            }
            composable(NavScreen.Favorite.route) {
                MainScreen(navController = navController, viewModel =viewModel , route = NavScreen.Favorite.route)
            }
            composable(NavScreen.Search.route) {
                MainScreen(navController = navController, viewModel =viewModel , route = NavScreen.Search.route)
            }
            composable(
                NavScreen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) {
                DetailScreen(
                    viewModel = hiltViewModel(),
                    it.arguments?.getString("id").orEmpty()
                ) {
                    navController.navigateUp()
                }
            }
        }
    }
  }

@Composable
fun MainScreen(navController: NavController,viewModel: DashboardViewModel,route:String){
    val items = listOf(
        NavScreen.Dashboard,
        NavScreen.Favorite,
        NavScreen.Search
    )
    Scaffold(
        topBar = { AppBar() },
        backgroundColor = MaterialTheme.colors.primarySurface,
        bottomBar = { BottomBar(navController = navController, items = items) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .background(colorResource(id = R.color.white))
        )
        {
            when(route){
                NavScreen.Dashboard.route -> BooksList(viewModel, navController)
                NavScreen.Search.route -> SearchScreen()
                NavScreen.Favorite.route -> FavoriteScreen()
            }
        }
    }
}

@Composable
fun BooksList(viewModel: DashboardViewModel,navController: NavController) {
    val lazyMovieItems: LazyPagingItems<BookResult> = viewModel.books.collectAsLazyPagingItems()
    LazyColumn {

        items(lazyMovieItems) { books ->
            BookItem(books, onClickStartSource = {
                navController.navigate("detailScreen/" + books?.id){
                    popUpTo("detailScreen/" + books?.id) {
                        inclusive = true
                    }
                }
            }
            )
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
fun BookItem(book: BookResult?,onClickStartSource : () -> Unit) {
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
    BookDescription(
        volumeInfo?.description.orEmpty(),
        modifier = Modifier.padding(16.dp)
    )
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
fun BookDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = description,
        style = MaterialTheme.typography.caption,
        overflow = TextOverflow.Clip,
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