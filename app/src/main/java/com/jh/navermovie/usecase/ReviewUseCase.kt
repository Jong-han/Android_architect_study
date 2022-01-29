package com.jh.navermovie.usecase

import com.jh.navermovie.data.local.db.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface ReviewUseCase {
    suspend fun insertReview(reviewEntity: ReviewEntity)
    suspend fun getAllReview(): Flow<List<ReviewEntity>>
    suspend fun getReview(title: String): ReviewEntity?
}