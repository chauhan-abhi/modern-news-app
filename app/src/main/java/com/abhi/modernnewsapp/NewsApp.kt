package com.abhi.modernnewsapp

import android.app.Application
import com.abhi.modernnewsapp.core.di.Injector

class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}