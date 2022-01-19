package com.jh.navermovie

import com.jh.navermovie.api.Movie
import com.jh.navermovie.api.MovieDataSource
import com.jh.navermovie.db.MovieDB
import com.jh.navermovie.db.ReviewEntity

interface Contract {
    interface SearchPresenter {
//        suspend fun searchMovie(searchString: String): MovieResult?
//        suspend fun getReview(movie: Movie): ReviewEntity?
//        suspend fun insertReview(reviewEntity: ReviewEntity)
        suspend fun onClickSearch(searchString: String)
        suspend fun onClickMovie(movie: Movie)
        fun initPresenter(view: View, dataSource: MovieDataSource, db: MovieDB)
    }
    interface View {
        fun submitSearchList(list: List<Movie>)
        fun controlVisibility(isEmpty: Boolean)
        fun showReviewDialog(movie: Movie, review: ReviewEntity?)
    }
}