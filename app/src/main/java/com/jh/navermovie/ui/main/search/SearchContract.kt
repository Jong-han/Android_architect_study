package com.jh.navermovie.ui.main.search

import com.jh.navermovie.api.Movie
import com.jh.navermovie.api.MovieDataSource
import com.jh.navermovie.db.MovieDB
import com.jh.navermovie.db.ReviewEntity

interface SearchContract {
    interface SearchPresenter {
        fun initPresenter(view: View, dataSource: MovieDataSource, db: MovieDB)
        suspend fun onClickSearch(searchString: String)
        suspend fun onClickMovie(movie: Movie)
    }
    interface View {
        fun submitSearchList(list: List<Movie>)
        fun controlVisibility(isEmpty: Boolean)
        fun showReviewDialog(movie: Movie, review: ReviewEntity?, insertReview: suspend (ReviewEntity)->Unit)
    }
}