package com.abhi.modernnewsapp.news.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.data.NewsUseCaseInteractor
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber


class NewsViewModel @ViewModelInject constructor(
    private val newsUseCaseInteractor: NewsUseCaseInteractor
): ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    var newsListLiveData: LiveData<ViewState<List<NewsArticleModel>>> = MutableLiveData()

    fun getNewsForCategory(category: String) {
        newsListLiveData = newsUseCaseInteractor
        .getNewsArticlesByCategory(category).asLiveData()
    }

    fun getNewsLiveData() = newsListLiveData

    fun bookMarkArticle(articleModel: NewsArticleModel) {
        viewModelScope.launch(exceptionHandler) {
            newsUseCaseInteractor.bookMarkArticle(articleModel)
        }
    }


}