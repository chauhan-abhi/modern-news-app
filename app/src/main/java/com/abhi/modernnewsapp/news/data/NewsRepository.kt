package com.abhi.modernnewsapp.news.data

import com.abhi.modernnewsapp.BuildConfig
import com.abhi.modernnewsapp.core.di.qualifier.CoroutineBackgroundDispatcher
import com.abhi.modernnewsapp.core.extensions.httpError
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.data.network.ArticleModel
import com.abhi.modernnewsapp.news.data.network.NewsApiService
import com.abhi.modernnewsapp.news.data.network.NewsResponse
import com.abhi.modernnewsapp.news.data.storage.NewsArticleDao
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import com.abhi.modernnewsapp.news.mapper.NewsMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val newsArticleDao: NewsArticleDao,
        private val newsApiService: NewsApiService,
        @CoroutineBackgroundDispatcher val ioDispatcher: dagger.Lazy<CoroutineDispatcher>
)  : NewsUseCaseInteractor, NewsMapper {
    override fun getNewsArticlesByCategory(category: String) : Flow<ViewState<List<NewsArticleModel>>> = flow {

        emit(ViewState.loading())

        val newsResponseFromServer = getTopHeadLinesForCategory(category)
        newsResponseFromServer.body()?.articles?.toCache(category)?.let(newsArticleDao::insert)

        val cachedNews = newsArticleDao.getAllArticlesForCategory(category)
        emitAll(cachedNews.map { ViewState.success(it) })

    }.flowOn(ioDispatcher.get())

    override suspend fun getTopHeadLinesForCategory(category: String): Response<NewsResponse> {
        return try {
            newsApiService.getTopHeadLinesForCategory(category +
                    "", "in", BuildConfig.NEWS_API_KEY)
        } catch (e: Exception) {
            httpError(404)
        }
    }

    override suspend fun bookMarkArticle(articleModel: NewsArticleModel) {
        withContext(ioDispatcher.get()) {
            newsArticleDao.updateArticle(articleModel)
        }
    }
}

@Module
@InstallIn(ApplicationComponent::class)
interface NewsRepositoryModule {
    @Binds fun it(it: NewsRepository) : NewsUseCaseInteractor
}