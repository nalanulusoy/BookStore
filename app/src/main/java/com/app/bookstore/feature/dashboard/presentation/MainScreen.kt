package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.bookstore.R
import com.app.bookstore.base.NavScreen
import com.app.bookstore.component.AppBarView
import com.app.bookstore.feature.detail.presentation.DetailScreen
import com.app.bookstore.feature.favorite.presentation.FavoriteScreen
import com.app.bookstore.feature.search.presentation.SearchScreen


/**
 * Created by Nalan Ulusoy on 05,Temmuz,2022
 */
@Composable
fun MainScreen(viewModel: DashboardViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = NavScreen.Dashboard.route,
        Modifier.padding()
    ) {
        composable(NavScreen.Dashboard.route) {
            CommonNavController(
                navController = navController,
                viewModel = viewModel,
                route = NavScreen.Dashboard.route
            )
        }
        composable(NavScreen.Favorite.route) {
            CommonNavController(
                navController = navController,
                viewModel = viewModel,
                route = NavScreen.Favorite.route
            )
        }
        composable(NavScreen.Search.route) {
            CommonNavController(
                navController = navController,
                viewModel = viewModel,
                route = NavScreen.Search.route
            )
        }
        composable(
            NavScreen.Detail.route,
            arguments = listOf(navArgument(NavScreen.Detail.id) { type = NavType.StringType })
        ) {
            DetailScreen(
                viewModel = hiltViewModel(),
                it.arguments?.getString(NavScreen.Detail.id).orEmpty()
            ) {
                navController.navigateUp()
            }
        }
    }
}

@Composable
fun CommonNavController(
    navController: NavController,
    viewModel: DashboardViewModel,
    route: String
) {
    val items = listOf(
        NavScreen.Dashboard,
        NavScreen.Favorite,
        NavScreen.Search
    )
    Scaffold(
        topBar = { AppBarView(title = stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.primarySurface,
        bottomBar = { BottomBar(navController = navController, items = items) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
                .background(colorResource(id = R.color.white))
        )
        {
            when (route) {
                NavScreen.Dashboard.route -> BooksList(viewModel, navController)
                NavScreen.Search.route -> SearchScreen(navController)
                NavScreen.Favorite.route -> FavoriteScreen(viewModel)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController, items: List<NavScreen>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.bottomBarData?.icon?.let { Icon(it, contentDescription = null) } },
                label = { screen.bottomBarData?.title?.let { Text(stringResource(it)) } },
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