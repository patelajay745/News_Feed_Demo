package com.ajay.example.newsappdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajay.example.newsappdemo.models.News
import com.ajay.example.newsappdemo.repository.NewsRepository
import kotlinx.coroutines.*

class TechnologyViewModel(private val newsRepository: NewsRepository):ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val newsList = MutableLiveData<News>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getTechnologyNews() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = newsRepository.getAllTechnologyNews()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body()!=null) {
                    newsList.postValue(response.body()!!)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}