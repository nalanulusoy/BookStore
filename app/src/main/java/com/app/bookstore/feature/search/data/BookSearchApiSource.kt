package com.app.bookstore.feature.search.data

import com.app.bookstore.base.extention.COMMON_API_ROUTE_BOOK_LIST
import com.app.bookstore.feature.dashboard.data.response.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
interface BookSearchApiSource {
    @GET(COMMON_API_ROUTE_BOOK_LIST)
    suspend fun getBookList(@Query("startIndex") startIndex: Int,
                            @Query("q") searchString: String,
                            @Query("key") apiKey: String
    ): BookListResponse
}