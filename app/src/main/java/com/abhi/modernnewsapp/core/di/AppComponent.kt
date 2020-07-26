package com.abhi.modernnewsapp.core.di

import com.abhi.modernnewsapp.NewsApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: NewsApp)

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule):Builder
        fun build(): AppComponent
    }

}
