package com.jh.navermovie.data.remote.service

import com.jh.navermovie.data.remote.response.MovieResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieService {
    @GET("movie.json")
    suspend fun getMovies(@Header("X-Naver-Client-Id") clientId: String = "WxREKNz1As2rOvxdY3Uk",
                          @Header("X-Naver-Client-Secret") clientSecret: String = "JGRuhinweu",
                          @Query("query") searchString: String): MovieResult
}