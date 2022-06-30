package com.app.bookstore.feature.detail.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@AndroidEntryPoint
class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           DetailScreen()
        }
    }
}