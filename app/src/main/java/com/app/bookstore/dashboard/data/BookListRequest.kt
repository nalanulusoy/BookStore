package com.app.bookstore.dashboard.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Nalan Ulusoy on 13,Mart,2022
 */
data class BookListRequest(@SerializedName("maxResults")
                              val maxResults: Int)