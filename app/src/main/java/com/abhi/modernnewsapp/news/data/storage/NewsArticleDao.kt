package com.abhi.modernnewsapp.news.data.storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Query("select * from news_article where category = :category order by publishedAt desc")
    fun getAllArticlesForCategory(category: String): Flow<List<NewsArticleModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: List<NewsArticleModel>)

    @Query("DELETE FROM news_article")
    fun clearAllArticles()



}