package com.abhi.modernnewsapp.news.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.data.NewsUseCaseInteractor
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel


class NewsViewModel @ViewModelInject constructor(
    private val newsUseCaseInteractor: NewsUseCaseInteractor
): ViewModel() {

    var newsListLiveData: LiveData<ViewState<List<NewsArticleModel>>> = MutableLiveData()

    fun getNewsForCategory(category: String) {
        newsListLiveData = newsUseCaseInteractor
        .getNewsArticlesByCategory(category).asLiveData()
    }

    fun getNewsLiveData() = newsListLiveData


}