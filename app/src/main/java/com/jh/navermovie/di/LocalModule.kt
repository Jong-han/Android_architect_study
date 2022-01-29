package com.jh.navermovie.di

import android.content.Context
import androidx.room.Room
import com.jh.navermovie.data.local.db.MovieDB
import com.jh.navermovie.data.repository.LocalRepository
import com.jh.navermovie.data.repository.LocalRepositoryImpl
import com.jh.navermovie.usecase.ReviewUseCase
import com.jh.navermovie.usecase.ReviewUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalProvidesModule {
    @Provides
    @Singleton
    fun providesMovieDB(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(context, MovieDB::class.java, "MovieDB.db").build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBindsModule {
    @Binds
    @Singleton
    abstract fun bindsLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

    @Binds
    @Singleton
    abstract fun bindsReviewUseCase(reviewUseCaseImpl: ReviewUseCaseImpl): ReviewUseCase
}