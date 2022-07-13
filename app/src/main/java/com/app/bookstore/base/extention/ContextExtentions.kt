package com.app.bookstore.base.extention

import android.content.Context
import android.content.Intent

import androidx.core.content.ContextCompat


/**
 * Created by Nalan Ulusoy on 08,Temmuz,2022
 */

fun Context.openIntentActionView(url:String){
    ContextCompat.startActivity(
        this,
        Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url)),
        null
    )
}