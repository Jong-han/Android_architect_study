package com.jh.navermovie.data.remote

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.MovieResult
import com.jh.navermovie.data.remote.service.MovieService
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(): MovieDataSource {

    override suspend fun getMovies(searchString: String): Resource<MovieResult> {
        return kotlin.runCatching {
            val response = RetrofitCreator.createRetrofit().create(MovieService::class.java).getMovies(searchString = searchString)
            Resource.Success(response)
        }.onFailure {
            return Resource.Error(it.message)
        }.getOrThrow()
    }


}