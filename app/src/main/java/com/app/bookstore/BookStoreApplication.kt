package com.app.bookstore

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Nalan Ulusoy on 11,Mart,2022
 */
@HiltAndroidApp
class BookStoreApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}