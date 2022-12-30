package com.ajay.example.newsappdemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.ajay.example.newsappdemo.viewmodels.BusinessViewModel
import com.ajay.example.newsappdemo.viewmodels.HealthViewModel
import com.ajay.example.newsappdemo.viewmodels.ScienceViewModel
import com.ajay.example.newsappdemo.viewmodels.SportsViewModel

class SportsViewModelFactory(private val newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SportsViewModel::class.java)) {
            SportsViewModel(this.newsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}