package com.ajay.example.newsappdemo.retro

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val Base_url="https://newsapi.org/"
    const val api_key="c474659cc91c473c8b0ffeb9418bbda3"

}