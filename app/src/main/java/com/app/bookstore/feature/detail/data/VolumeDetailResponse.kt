package com.app.bookstore.feature.detail.data

import com.app.bookstore.feature.dashboard.data.response.VolumeInfo


/**
* Created by Nalan Ulusoy on 04,Temmuz,2022
*/
data class VolumeDetailResponse(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)