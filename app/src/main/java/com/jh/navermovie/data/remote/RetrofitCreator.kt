package com.jh.navermovie.data.remote

import com.jh.navermovie.BuildConfig
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
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun createOkHttpClient(): OkHttpClient {

            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            val builder = OkHttpClient.Builder()
            builder.addNetworkInterceptor(interceptor)

            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        }
    }
}