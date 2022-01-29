package com.jh.navermovie.usecase

import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.Movie
import com.jh.navermovie.data.remote.response.MovieResult
import com.jh.navermovie.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val remoteRepository: RemoteRepository): SearchUseCase {
    override fun getFilteredMovies(searchString: String): Flow<Resource<MovieResult>> {
        return remoteRepository.getMovies(searchString).map {
            when (it) {
                is Resource.Loading -> it
                is Resource.Success -> Resource.Success(MovieResult(
                    it.data?.items?.filter { movie ->
                        (!movie.image.isNullOrEmpty() && movie.userRating != "0.00")
                    } as ArrayList<Movie>
                ))
                is Resource.Error -> it
            }
        }
    }
}