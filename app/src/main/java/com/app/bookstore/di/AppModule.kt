package com.app.bookstore.di

import android.content.Context
import com.app.bookstore.db.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Nalan Ulusoy on 12,Mart,2022
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideBookDatabase(@ApplicationContext context: Context): BookDatabase =
        BookDatabase.create(context)
}