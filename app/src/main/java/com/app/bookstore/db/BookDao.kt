package com.app.bookstore.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nalan Ulusoy on 13,Temmuz,2022
 */
@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: BookData)

    @Query("SELECT * FROM fav_book_keys")
     fun getFavoriteBooksData(): Flow<List<BookData>>

    @Delete
    suspend fun delete(book:BookData)

}