package com.app.bookstore.feature.dashboard.data.response

import com.google.gson.annotations.SerializedName

data class ImageLinks (@SerializedName( "smallThumbnail") val smallThumbnail: String? = null,
                       @SerializedName( "thumbnail") val thumbnail: String? = null)
