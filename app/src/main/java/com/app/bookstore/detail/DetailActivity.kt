package com.app.bookstore.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.bookstore.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance())
                .commitNow()
        }
    }
}