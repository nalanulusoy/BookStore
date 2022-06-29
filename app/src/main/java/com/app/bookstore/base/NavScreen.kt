package com.app.bookstore.base

import androidx.annotation.StringRes
import com.app.bookstore.R


/**
 * Created by Nalan Ulusoy on 29,Haziran,2022
 */
sealed class NavScreen (val route: String,@StringRes val resourceId: Int) {
    object Dashboard : NavScreen("dashboardScreen", R.string.home_title)
}