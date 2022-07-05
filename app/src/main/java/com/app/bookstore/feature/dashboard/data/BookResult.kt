package com.app.bookstore.feature.dashboard.data

import com.app.bookstore.feature.detail.data.AccessInfo
import com.google.gson.annotations.SerializedName


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */

data class BookResult(@SerializedName( "id") val id: String?,
                      @SerializedName( "kind") val kind: String?,
                      @SerializedName( "selfLink") val selfLink: String?,
                      @SerializedName("volumeInfo") val volumeInfo: VolumeInfo?,
                      @SerializedName("accessInfo") val accessInfo: AccessInfo?,

                      )