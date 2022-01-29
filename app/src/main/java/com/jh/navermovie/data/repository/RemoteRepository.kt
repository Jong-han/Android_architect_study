package com.jh.navermovie.data.repository

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.MovieResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getMovies(searchString: String): Flow<Resource<MovieResult>>
}