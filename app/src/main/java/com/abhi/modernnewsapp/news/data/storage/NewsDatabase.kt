package com.abhi.modernnewsapp.news.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsArticleModel::class],
    version = 2,
    exportSchema = true
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticleDao
}