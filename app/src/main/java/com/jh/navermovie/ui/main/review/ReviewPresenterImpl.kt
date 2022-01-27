package com.jh.navermovie.ui.main.review

import com.jh.navermovie.data.local.db.MovieDB
import com.jh.navermovie.data.local.db.ReviewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReviewPresenterImpl: ReviewContract.ReviewPresenter {

    private var view: ReviewContract.View? = null
    private var db: MovieDB? = null

    override fun initPresenter(view: ReviewContract.View, db: MovieDB) {
        this.view = view
        this.db = db
    }

    private fun getReview(): List<ReviewEntity>? {
        return db?.reviewDAO()?.getAll()
    }

    override suspend fun onResumeReview() {
        val reviewList: List<ReviewEntity>?
        withContext(Dispatchers.IO) {
            reviewList = getReview()
        }
        reviewList?.let { view?.submitReviewList(it) }
    }
}