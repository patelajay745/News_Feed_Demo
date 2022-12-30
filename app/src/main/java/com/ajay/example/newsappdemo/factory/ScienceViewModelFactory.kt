package com.ajay.example.newsappdemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.ajay.example.newsappdemo.viewmodels.BusinessViewModel
import com.ajay.example.newsappdemo.viewmodels.HealthViewModel
import com.ajay.example.newsappdemo.viewmodels.ScienceViewModel

class ScienceViewModelFactory(private val newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ScienceViewModel::class.java)) {
            ScienceViewModel(this.newsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}