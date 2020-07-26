package com.abhi.modernnewsapp.news.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "NewsArticles"
)
data class ArticleEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @ColumnInfo(name = NewsArticles.Column.author)
        val author: String? = null,

        @ColumnInfo(name = NewsArticles.Column.title)
        val title: String? = null,

        @ColumnInfo(name = NewsArticles.Column.description)
        val description: String? = null,

        @ColumnInfo(name = NewsArticles.Column.url)
        val url: String? = null,

        @ColumnInfo(name = NewsArticles.Column.urlToImage)
        val urlToImage: String? = null,

        @ColumnInfo(name = NewsArticles.Column.publishedAt)
        val publishedAt: String? = null,

        @ColumnInfo(name = NewsArticles.Column.content)
        val content: String? = null

) {

    object NewsArticles {
        const val tableName = "news_article"

        object Column {
            const val id = "id"
            const val author = "author"
            const val title = "title"
            const val description = "description"
            const val url = "url"
            const val urlToImage = "urlToImage"
            const val publishedAt = "publishedAt"
            const val content = "content"
        }
    }
}