package com.jh.navermovie.usecase

import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewUseCaseImpl @Inject constructor(private val localRepository: LocalRepository): ReviewUseCase {
    override suspend fun insertReview(reviewEntity: ReviewEntity) {
        return localRepository.insertReview(reviewEntity)
    }

    override suspend fun getAllReview(): Flow<List<ReviewEntity>> {
        return localRepository.getAllReview()
    }

    override suspend fun getReview(title: String): ReviewEntity? {
        return localRepository.getReview(title)
    }
}