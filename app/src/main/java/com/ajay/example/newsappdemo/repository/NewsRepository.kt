package com.ajay.example.newsappdemo.repository

import com.ajay.example.newsappdemo.retro.RetrofitService

class NewsRepository(
    private val retrofitService: RetrofitService,
) {

    suspend fun getAllBusinessNews() =
        retrofitService.getNews("us", "business", "c474659cc91c473c8b0ffeb9418bbda3")

    suspend fun getAllEntertainmentNews() =
        retrofitService.getNews("us", "entertainment", "c474659cc91c473c8b0ffeb9418bbda3")

    suspend fun getAllHealthNews() =
        retrofitService.getNews("us", "health", "c474659cc91c473c8b0ffeb9418bbda3")

    suspend fun getAllScienceNews() =
        retrofitService.getNews("us", "science", "c474659cc91c473c8b0ffeb9418bbda3")

    suspend fun getAllSportsNews() =
        retrofitService.getNews("us", "sports", "c474659cc91c473c8b0ffeb9418bbda3")

    suspend fun getAllTechnologyNews() =
        retrofitService.getNews("us", "technology", "c474659cc91c473c8b0ffeb9418bbda3")


}