package com.jh.navermovie.data.repository

import com.jh.navermovie.data.local.db.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertReview(reviewEntity: ReviewEntity)
    suspend fun getAllReview(): Flow<List<ReviewEntity>>
    suspend fun getReview(title: String): ReviewEntity?
}