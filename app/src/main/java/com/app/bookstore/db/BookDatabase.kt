package com.app.bookstore.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.bookstore.base.extention.DB_NAME

/**
 * Created by Nalan Ulusoy on 13,Temmuz,2022
 */
@Database(entities = [BookData::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): BookDatabase {
            val databaseBuilder = Room.databaseBuilder(context, BookDatabase::class.java, DB_NAME)
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getDao(): BookDao
}
