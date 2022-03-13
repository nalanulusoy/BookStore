package com.app.bookstore.dashboard.data

import com.app.bookstore.base.extention.COMMON_API_ROUTE_BOOK_LIST
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */
interface BookListApiService {

    @POST(COMMON_API_ROUTE_BOOK_LIST)
    suspend fun getBookList(
        @Query("maxResults") maxResults: String?
    ): Response<BookListResponse>
}