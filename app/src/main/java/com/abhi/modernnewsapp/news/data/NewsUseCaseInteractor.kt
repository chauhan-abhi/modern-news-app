package com.abhi.modernnewsapp.news.data

import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.data.network.ArticleModel
import com.abhi.modernnewsapp.news.data.network.NewsResponse
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsUseCaseInteractor {

    fun getNewsArticlesByCategory(category: String) : Flow<ViewState<List<NewsArticleModel>>>

    suspend fun bookMarkArticle(articleModel: NewsArticleModel)

    suspend fun getTopHeadLinesForCategory(category: String): Response<NewsResponse>
}