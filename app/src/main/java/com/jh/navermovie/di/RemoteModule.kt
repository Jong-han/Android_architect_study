package com.jh.navermovie.di

import com.jh.navermovie.data.remote.MovieDataSource
import com.jh.navermovie.data.remote.MovieDataSourceImpl
import com.jh.navermovie.data.repository.RemoteRepository
import com.jh.navermovie.data.repository.RemoteRepositoryImpl
import com.jh.navermovie.usecase.SearchUseCase
import com.jh.navermovie.usecase.SearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBindsModule {
    @Binds
    @Singleton
    abstract fun bindsMovieUseCase(movieUseCaseImpl: SearchUseCaseImpl): SearchUseCase

    @Binds
    @Singleton
    abstract fun bindsRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    @Singleton
    abstract fun bindsMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource

}

