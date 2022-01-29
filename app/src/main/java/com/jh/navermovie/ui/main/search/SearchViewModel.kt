package com.jh.navermovie.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.data.remote.response.Movie
import com.jh.navermovie.usecase.ReviewUseCase
import com.jh.navermovie.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase, private val reviewUseCase: ReviewUseCase): ViewModel() {

    sealed class Event {
        class ShowToast(val string: String): Event()
        class SetVisibility(val isEmpty: Boolean): Event()
    }

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    val searchString = MutableLiveData<String>("")

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    fun getFilteredMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.getFilteredMovies(searchString.value ?: "").collect {
                when (it) {
                    is Resource.Loading -> { emitEvent(Event.ShowToast("영화 정보를 가져올게요.")) }
                    is Resource.Success -> {
                        emitEvent(Event.ShowToast("영화 정보를 가져왔어요."))
                        _movieList.value = it.data?.items?.toList() ?: emptyList()
                        emitEvent(Event.SetVisibility(_movieList.value.isNullOrEmpty()))
                    }
                    is Resource.Error -> {
                        emitEvent(Event.ShowToast("영화 정보 가져오지 못했어요."))
                    }
                }
            }
        }
    }

    private fun emitEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    suspend fun getReview(title: String): ReviewEntity? {
        return reviewUseCase.getReview(title)
    }

    suspend fun insertReview(reviewEntity: ReviewEntity) {
        reviewUseCase.insertReview(reviewEntity)
    }

}