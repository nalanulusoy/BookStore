package com.app.bookstore.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


/**
 * Created by Nalan Ulusoy on 13,Temmuz,2022
 */
@Entity(tableName = "fav_book_keys")
data class BookData(
    val publishData: String?, val title: String?, val imageUrl: String?, @PrimaryKey
    var id: String
) : Serializable
