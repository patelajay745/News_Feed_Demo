package com.ajay.example.newsappdemo.retro

import com.ajay.example.newsappdemo.models.News
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("v2/top-headlines")
    suspend fun getNews(@Query("country")country:String, @Query("category")category:String, @Query("apikey")api_key:String): Response<News>


    companion object {
        val interceptor= HttpLoggingInterceptor().apply {
            this.level= HttpLoggingInterceptor.Level.BODY
        }

        val client= OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(RetrofitHelper.Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

    //v2/top-headlines?country=us&category=business&apiKey=c474659cc91c473c8b0ffeb9418bbda3
}