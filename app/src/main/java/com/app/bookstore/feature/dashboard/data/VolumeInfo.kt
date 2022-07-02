package com.app.bookstore.feature.dashboard.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */

data class VolumeInfo (    @SerializedName("title") val title : String,
                           @SerializedName("authors") val authors : List<String>?,
                           @SerializedName("publisher") val publisher : String,
                           @SerializedName("publishedDate") val publishedDate : String,
                           @SerializedName("description") val description : String,
                           @SerializedName("pageCount") val pageCount : Int,
                           @SerializedName("categories") val categories : List<String>?,
                           @SerializedName("imageLinks") val imageLinks : ImageLinks?,
                           @SerializedName("language") val language : String
)