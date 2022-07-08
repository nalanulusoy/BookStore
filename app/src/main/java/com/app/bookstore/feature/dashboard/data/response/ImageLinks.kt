package com.app.bookstore.feature.dashboard.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nalan Ulusoy on 04,Temmuz,2022
 */
data class ImageLinks(
    @SerializedName("smallThumbnail") val smallThumbnail: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null
)
