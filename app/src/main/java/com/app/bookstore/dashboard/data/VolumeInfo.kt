package com.app.bookstore.dashboard.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */

data class VolumeInfo (@SerializedName( "title") val title: String,
                       @SerializedName( "subtitle") val subtitle: String,
                       @SerializedName( "authors") val authors: List<String>,
                       @SerializedName( "description")  val description: String)