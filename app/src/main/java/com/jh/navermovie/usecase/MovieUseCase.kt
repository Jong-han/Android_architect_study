package com.jh.navermovie.usecase

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getFilteredMovies(searchString: String): Flow<Resource<MovieResult>>
}