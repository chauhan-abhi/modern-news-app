package com.abhi.modernnewsapp.core.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

typealias BaseActivity = NewsActivity


@AndroidEntryPoint
abstract class NewsActivity: AppCompatActivity()