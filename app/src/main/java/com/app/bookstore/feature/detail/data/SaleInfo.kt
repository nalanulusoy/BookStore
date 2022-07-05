package com.app.bookstore.feature.detail.data

data class SaleInfo(
    val buyLink: String,
    val country: String,
    val isEbook: Boolean,
    val listPrice: ListPrice?,
    val saleability: String
)
