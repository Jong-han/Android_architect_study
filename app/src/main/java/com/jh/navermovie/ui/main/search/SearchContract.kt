package com.jh.navermovie.ui.main.search

import com.jh.navermovie.data.local.db.MovieDB
import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.data.remote.MovieDataSourceImpl
import com.jh.navermovie.data.remote.response.Movie

interface SearchContract {
    interface SearchPresenter {
        fun initPresenter(view: View, dataSourceImpl: MovieDataSourceImpl, db: MovieDB)
        suspend fun onClickSearch(searchString: String)
        suspend fun onClickMovie(movie: Movie)
    }
    interface View {
        fun submitSearchList(list: List<Movie>)
        fun controlVisibility(isEmpty: Boolean)
        fun showReviewDialog(movie: Movie, review: ReviewEntity?, insertReview: suspend (ReviewEntity)->Unit)
    }
}