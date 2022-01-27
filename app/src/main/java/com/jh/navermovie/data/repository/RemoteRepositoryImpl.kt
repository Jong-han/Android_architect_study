package com.jh.navermovie.data.repository

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.MovieDataSource
import com.jh.navermovie.data.remote.response.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource): RemoteRepository {
    override fun getMovies(searchString: String): Flow<Resource<MovieResult>> = flow {
        emit(Resource.Loading())
        emit(dataSource.getMovies(searchString))
    }.flowOn(Dispatchers.IO)
}