package com.jh.navermovie.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

class MovieDataSource {

    suspend fun getMovies(searchString: String): MovieResult? {
        return kotlin.runCatching {
            RetrofitCreator.createRetrofit().create(IService::class.java).getMovies(searchString = searchString)
        }.onFailure {
            return null
        }.getOrNull()
    }

    interface IService {
        @GET("movie.json")
        suspend fun getMovies(@Header("X-Naver-Client-Id") clientId: String = "WxREKNz1As2rOvxdY3Uk",
                              @Header("X-Naver-Client-Secret") clientSecret: String = "JGRuhinweu",
                              @Query("query") searchString: String): MovieResult
    }
}