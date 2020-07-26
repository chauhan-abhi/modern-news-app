package com.abhi.modernnewsapp.news.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 2
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticleDao
}