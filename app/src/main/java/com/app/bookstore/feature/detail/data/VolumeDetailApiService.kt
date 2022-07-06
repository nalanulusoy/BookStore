package com.app.bookstore.feature.detail.data

import com.app.bookstore.base.extention.COMMON_API_ROUTE_BOOK_DETAIL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Nalan Ulusoy on 04,Temmuz,2022
 */
interface VolumeDetailApiService {

    @GET(COMMON_API_ROUTE_BOOK_DETAIL)
    suspend fun getVolumeDetail(@Path("id") id: String?): Response<VolumeDetailResponse>
}