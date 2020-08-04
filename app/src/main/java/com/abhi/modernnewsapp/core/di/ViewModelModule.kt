package com.abhi.modernnewsapp.core.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhi.modernnewsapp.news.data.NewsUseCaseInteractor
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

abstract class ViewModelModule {


   /* @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: NewsViewModelFactory) : ViewModelProvider.Factory
*/
    /*@Provides
    fun providesViewModelFactory(map: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory = NewsViewModelFactory(map)*/
}