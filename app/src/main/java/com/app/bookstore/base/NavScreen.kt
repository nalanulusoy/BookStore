package com.app.bookstore.base

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 29,Haziran,2022
 */
sealed class NavScreen (val route: String,@StringRes val resourceId: Int,val bottomBarData: BottomBarData? = null
) {
    object Dashboard : NavScreen("dashboardScreen", R.string.home_title,BottomBarData.DASHBOARD)
    object Favorite : NavScreen("favoriteScreen", R.string.favorite_title,BottomBarData.FAVORITE)
    object Search : NavScreen("searchScreen", R.string.search_title,BottomBarData.SEARCH)
    object Detail : NavScreen("detailScreen", R.string.detail_title)
}

enum class BottomBarData( @StringRes val title: Int, val icon: ImageVector) {
    DASHBOARD(R.string.home_title, Icons.Filled.Home),
    FAVORITE(R.string.favorite_title, Icons.Filled.Favorite),
    SEARCH(R.string.search_title, Icons.Filled.Search);
}