package com.abhi.modernnewsapp.news.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abhi.modernnewsapp.news.data.NewsRepository
import com.abhi.modernnewsapp.news.data.NewsUseCaseInteractor


class NewsViewModel @ViewModelInject constructor(
    private val newsUseCaseInteractor: NewsUseCaseInteractor
): ViewModel() {

    fun getNewsForCategory(category: String) = newsUseCaseInteractor.getNewsArticlesByCategory(category).asLiveData()


}