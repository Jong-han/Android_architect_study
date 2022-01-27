package com.jh.navermovie.ui.main.review

import com.jh.navermovie.data.local.db.MovieDB
import com.jh.navermovie.data.local.db.ReviewEntity

interface ReviewContract {
    interface ReviewPresenter {
        fun initPresenter(view: View, db: MovieDB)
        suspend fun onResumeReview()
    }
    interface View {
        fun submitReviewList(list: List<ReviewEntity>)
    }
}