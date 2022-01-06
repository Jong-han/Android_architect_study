package com.jh.navermovie.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {
    companion object {
        private const val baseUrl = "https://openapi.naver.com/v1/search/"

        fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}