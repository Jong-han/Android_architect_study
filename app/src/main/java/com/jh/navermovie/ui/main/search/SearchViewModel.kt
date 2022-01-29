package com.jh.navermovie.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.data.remote.response.MovieResult
import com.jh.navermovie.usecase.ReviewUseCase
import com.jh.navermovie.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase, private val reviewUseCase: ReviewUseCase): ViewModel() {

    val searchString = MutableLiveData<String>("")

    fun getFilteredMovies(): Flow<Resource<MovieResult>> {
        return searchUseCase.getFilteredMovies(searchString.value ?: "")
    }

    suspend fun getReview(title: String): ReviewEntity? {
        return reviewUseCase.getReview(title)
    }

    suspend fun insertReview(reviewEntity: ReviewEntity) {
        reviewUseCase.insertReview(reviewEntity)
    }

}