package com.abhi.modernnewsapp.core.di

import com.abhi.modernnewsapp.NewsApp

object Injector {
    lateinit var appComponent: AppComponent

    fun init(app: NewsApp) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
        appComponent.inject(app)
    }
}