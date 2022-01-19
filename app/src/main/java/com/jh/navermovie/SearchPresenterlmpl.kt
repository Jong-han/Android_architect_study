package com.jh.navermovie

import com.jh.navermovie.api.Movie
import com.jh.navermovie.api.MovieDataSource
import com.jh.navermovie.api.MovieResult
import com.jh.navermovie.db.MovieDB
import com.jh.navermovie.db.ReviewEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPresenterImpl: Contract.SearchPresenter {

    private var dataSource: MovieDataSource? = null
    private var db: MovieDB? = null
    private var view: Contract.View? = null

    override fun initPresenter(view: Contract.View, dataSource: MovieDataSource, db: MovieDB) {
        this.dataSource = dataSource
        this.view = view
        this.db = db
    }

    private suspend fun searchMovie(searchString: String): MovieResult? {
        return dataSource?.getMovies(searchString)
    }

    private suspend fun getReview(movie: Movie): ReviewEntity? {
        return movie.title?.let { db?.reviewDAO()?.getRate(it) }
    }

    private suspend fun insertReview(reviewEntity: ReviewEntity) {
        db?.reviewDAO()?.insert(reviewEntity)
    }

    override suspend fun onClickSearch(searchString: String) {
        val list: List<Movie>
        withContext(Dispatchers.IO) {
            val searchResult = searchMovie(searchString)
            list = searchResult?.items?.filter { (!it.image.isNullOrEmpty() && it.userRating != "0.00") } ?: emptyList()
        }
        view?.run {
            submitSearchList(list)
            controlVisibility(list.isEmpty())
        }
    }

    override suspend fun onClickMovie(movie: Movie) {
        val review: ReviewEntity?
        withContext(Dispatchers.IO) {
            review = getReview(movie)
        }
        view?.showReviewDialog(movie, review)
    }

}