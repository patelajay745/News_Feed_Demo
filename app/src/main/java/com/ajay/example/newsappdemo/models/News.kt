package com.ajay.example.newsappdemo.models


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("articles")
    val articles: List<Article>
)