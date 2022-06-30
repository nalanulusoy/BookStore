package com.app.bookstore.feature.detail.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.bookstore.R
import com.app.bookstore.base.NavScreen


/**
 * Created by Nalan Ulusoy on 30,Haziran,2022
 */

@Composable
fun DetailScreen(){
    val  navController = rememberNavController()
    Scaffold(topBar = { AppBar()}) { innerPadding ->
        NavHost(navController, startDestination = NavScreen.Dashboard.route, Modifier.padding(innerPadding)) {
            composable(NavScreen.Detail.route) {

            }
        }
    }
}

@Preview
@Composable
fun AppBar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Green,
        modifier = Modifier.height(60.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically),
            text = stringResource(R.string.detail_title),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}