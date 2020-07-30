package com.abhi.modernnewsapp.news.data.network

import com.abhi.modernnewsapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines?=apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getTopHeadLinesForCategory(
            @Query("category") category: String,
            @Query("country") country: String): Response<NewsResponse>
}