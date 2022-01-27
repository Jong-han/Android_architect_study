package com.jh.navermovie.di

import android.content.Context
import androidx.room.Room
import com.jh.navermovie.data.local.db.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun providesMovieDB(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(context, MovieDB::class.java, "MovieDB.db").build()
    }
}