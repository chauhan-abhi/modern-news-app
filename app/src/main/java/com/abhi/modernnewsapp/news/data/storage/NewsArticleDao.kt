package com.abhi.modernnewsapp.news.data.storage

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Query("select * from news_article where category = category")
    fun getAllArticlesForCategory(category: String): Flow<List<NewsArticleModel>>


}