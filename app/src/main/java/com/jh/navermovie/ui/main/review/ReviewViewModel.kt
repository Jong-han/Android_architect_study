package com.jh.navermovie.ui.main.review

import androidx.lifecycle.ViewModel
import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.usecase.ReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val reviewUseCase: ReviewUseCase): ViewModel() {

    suspend fun getReviewList(): Flow<List<ReviewEntity>> = withContext(Dispatchers.IO) {
        reviewUseCase.getAllReview()
    }
}