package com.jh.navermovie.data.remote.response

data class MovieResult(
    var items: ArrayList<Movie>? = null
)

data class Movie(
    var title: String? = "",
    var image: String? = "",
    var director: String? = "",
    var actor: String? = "",
    var userRating: String? = "",
    var pubDate: String? = "",
    var link: String? = ""
)