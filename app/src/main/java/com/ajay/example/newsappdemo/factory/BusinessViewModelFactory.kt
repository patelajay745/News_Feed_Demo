package com.ajay.example.newsappdemo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajay.example.newsappdemo.repository.NewsRepository
import com.ajay.example.newsappdemo.viewmodels.BusinessViewModel

class BusinessViewModelFactory(private val newsRepository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BusinessViewModel::class.java)) {
            BusinessViewModel(this.newsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}