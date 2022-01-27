package com.jh.navermovie.ui.main.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.remote.response.MovieResult
import com.jh.navermovie.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    val searchString = MutableLiveData<String>("")

//    fun getFilteredMovies() = movieUseCase.getFilteredMovies(searchString.value ?: "")
fun getFilteredMovies(): Flow<Resource<MovieResult>> {
    Log.i("asdf","searchString :: ${searchString.value}")
    return movieUseCase.getFilteredMovies(searchString.value ?: "")
}

}