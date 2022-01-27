package com.jh.navermovie.ui.main.search

class SearchPresenterImpl {

//    private var dataSourceImpl: MovieDataSourceImpl? = null
//    private var db: MovieDB? = null
//    private var view: SearchContract.View? = null
//
//    override fun initPresenter(view: SearchContract.View, dataSourceImpl: MovieDataSourceImpl, db: MovieDB) {
//        this.dataSourceImpl = dataSourceImpl
//        this.view = view
//        this.db = db
//    }
//
//    private suspend fun searchMovie(searchString: String): MovieResult? {
//        return dataSourceImpl?.getMovies(searchString)
//    }
//
//    private fun getReview(movie: Movie): ReviewEntity? {
//        return movie.title?.let { db?.reviewDAO()?.getRate(it) }
//    }
//
//    private fun insertReview(reviewEntity: ReviewEntity) {
//        db?.reviewDAO()?.insert(reviewEntity)
//    }
//
//    override suspend fun onClickSearch(searchString: String) {
//        val list: List<Movie>
//        withContext(Dispatchers.IO) {
//            val searchResult = searchMovie(searchString)
//            list = searchResult?.items?.filter { (!it.image.isNullOrEmpty() && it.userRating != "0.00") } ?: emptyList()
//        }
//        view?.run {
//            submitSearchList(list)
//            controlVisibility(list.isEmpty())
//        }
//    }
//
//    override suspend fun onClickMovie(movie: Movie) {
//        val review: ReviewEntity?
//        withContext(Dispatchers.IO) {
//            review = getReview(movie)
//        }
//        view?.showReviewDialog(movie, review) { reviewEntity: ReviewEntity ->
//            withContext(Dispatchers.IO) {
//                insertReview(reviewEntity)
//            }
//        }
//    }

}