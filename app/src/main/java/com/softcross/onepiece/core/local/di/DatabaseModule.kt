package com.softcross.onepiece.core.local.di

import android.content.Context
import androidx.room.Room
import com.softcross.onepiece.core.local.OnePieceFavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OnePieceFavoriteDatabase =
        Room.databaseBuilder(
            context = context,
            klass = OnePieceFavoriteDatabase::class.java,
            name = "one_piece_fav_database"
        ).build()

    @Provides
    fun provideDao(database: OnePieceFavoriteDatabase) = database.favoritesDao()
}