package com.jh.navermovie.data.repository

import com.jh.navermovie.data.local.db.MovieDB
import com.jh.navermovie.data.local.db.ReviewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val db: MovieDB): LocalRepository {

    private val reviewDAO = db.reviewDAO()

    override suspend fun insertReview(reviewEntity: ReviewEntity) {
        reviewDAO.insert(reviewEntity)
    }

    override suspend fun getAllReview(): Flow<List<ReviewEntity>> {
        return reviewDAO.getAll()
    }

    override suspend fun getReview(title: String): ReviewEntity? {
        return reviewDAO.getReview(title)
    }
}