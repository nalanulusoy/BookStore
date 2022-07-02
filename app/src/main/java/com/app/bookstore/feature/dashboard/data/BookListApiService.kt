package com.app.bookstore.feature.dashboard.data

import com.app.bookstore.base.extention.COMMON_API_ROUTE_BOOK_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */
interface BookListApiService {

    @GET(COMMON_API_ROUTE_BOOK_LIST)
    suspend fun getBookList(@Query("startIndex") startIndex: Int,
            @Query("q") searchString: String,
            @Query("key") apiKey: String
    ): BookListResponse

    @GET("volumes/{id}")
    fun getBookDetail(
        @Path("id") id: String
    ): BookListResponse
}