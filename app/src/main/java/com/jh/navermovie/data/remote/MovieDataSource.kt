package com.jh.navermovie.data.remote

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.MovieResult

interface MovieDataSource {
    suspend fun getMovies(searchString: String): Resource<MovieResult>
}