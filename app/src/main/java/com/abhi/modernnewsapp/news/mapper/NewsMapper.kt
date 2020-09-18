package com.abhi.modernnewsapp.news.mapper

import com.abhi.modernnewsapp.core.Mapper
import com.abhi.modernnewsapp.news.data.network.ArticleModel
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel

interface NewsMapper : Mapper<NewsArticleModel, ArticleModel> {
    override fun NewsArticleModel.toRemote(): ArticleModel {
        return ArticleModel(
                null,
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content
        )
    }

    override fun ArticleModel.toCache(category: String): NewsArticleModel {
        return NewsArticleModel(
                author = author,
                title = title?: "Default Title",
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content,
                category = category,
                isBookmarked = false
        )
    }
}