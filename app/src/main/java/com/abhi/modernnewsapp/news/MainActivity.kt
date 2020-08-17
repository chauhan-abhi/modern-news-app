package com.abhi.modernnewsapp.news

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.base.BaseActivity
import com.abhi.modernnewsapp.core.di.Injector
import com.abhi.modernnewsapp.core.extensions.observeNotNull
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.ui.adapter.NewsListingPagerAdapter
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {

    lateinit var pagerAdapter: NewsListingPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = NewsListingPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewpager)
        viewpager.adapter = pagerAdapter
        viewpager.offscreenPageLimit = 2
        //setSupportActionBar(mToolbar)
       /* TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = categoryTitles[position]
        }.attach()*/
        mToolbar.title = "Hi, Abhi!"
        setSupportActionBar(mToolbar)
        //tabLayout.getTabAt()
        /*button.setOnClickListener {
            newsViewModel.getNewsForCategory("technology").observeNotNull(this) { state ->
                when(state) {
                    is ViewState.Success -> Log.d("RES: ", state.data.toString())
                    is ViewState.Loading -> Log.d("RES: ", "Loading..")
                    is ViewState.Error   -> Log.d("RES: ", "Error...")
                }
            }
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val itemSearch = menu.findItem(R.id.action_search)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}