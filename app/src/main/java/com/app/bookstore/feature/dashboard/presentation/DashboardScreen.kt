package com.app.bookstore.feature.dashboard.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.app.bookstore.R
import com.app.bookstore.base.NavScreen
import com.app.bookstore.feature.favorite.FavoriteScreen
import com.app.bookstore.feature.search.SearchScreen


/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */
@Preview
@Composable
fun DashboardScreen(){
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