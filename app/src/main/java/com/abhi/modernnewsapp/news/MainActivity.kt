package com.abhi.modernnewsapp.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.base.BaseActivity
import com.abhi.modernnewsapp.core.di.Injector
import com.abhi.modernnewsapp.core.extensions.observeNotNull
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModelFactory
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Injector.appComponent.inject(this)
        button.setOnClickListener {
            newsViewModel.getNewsForCategory("technology").observeNotNull(this) { state ->
                when(state) {
                    is ViewState.Success -> Log.d("RES: ", state.data.toString())
                    is ViewState.Loading -> Log.d("RES: ", "Loading..")
                    is ViewState.Error   -> Log.d("RES: ", "Error...")
                }
            }
        }

    }
}