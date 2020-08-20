package com.abhi.modernnewsapp.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.base.BaseActivity
import com.abhi.modernnewsapp.news.ui.adapter.NewsListingPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {

    lateinit var pagerAdapter: NewsListingPagerAdapter

    private val categoryTitles = arrayListOf(
        "Latest News",
        "General",
        "Entertainment",
        "Sports",
        "Business",
        "Technology",
        "Science",
        "Health"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagerAdapter = NewsListingPagerAdapter(this)
        viewpager.adapter = pagerAdapter
        //setSupportActionBar(mToolbar)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = categoryTitles[position]
        }.attach()
        viewpager.offscreenPageLimit = 2
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

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}