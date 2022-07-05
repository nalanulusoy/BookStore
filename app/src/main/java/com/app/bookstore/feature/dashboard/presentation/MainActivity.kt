package com.app.bookstore.feature.dashboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import com.app.bookstore.feature.dashboard.presentation.DashboardViewModel
import com.app.bookstore.feature.dashboard.presentation.MainScreen
import com.app.myapplication.ui.theme.MyApplicationTheme

/**
 * Created by nalanulusoy on 11,MARCH,2022
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen(viewModel)
            }
        }
    }
}
