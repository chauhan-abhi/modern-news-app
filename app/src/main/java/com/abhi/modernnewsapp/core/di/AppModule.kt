package com.abhi.modernnewsapp.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.abhi.modernnewsapp.BuildConfig
import com.abhi.modernnewsapp.news.data.network.NewsApiService
import com.abhi.modernnewsapp.news.data.storage.NewsArticleDao
import com.abhi.modernnewsapp.news.data.storage.NewsDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: Application): Context = app.applicationContext

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            //.addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY).build()
                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArticleDb(app:Application): NewsDatabase {
        return Room
                .databaseBuilder(app, NewsDatabase::class.java, "news.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDatabase) : NewsArticleDao = db.newsArticlesDao()

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }
}