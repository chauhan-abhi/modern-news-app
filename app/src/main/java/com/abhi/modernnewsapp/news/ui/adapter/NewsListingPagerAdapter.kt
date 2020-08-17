package com.abhi.modernnewsapp.news.ui.adapter

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhi.modernnewsapp.news.ui.NewsListFragment

class NewsListingPagerAdapter(fragment: FragmentManager): FragmentStatePagerAdapter(fragment) {

    private val categories = arrayListOf(
        "",
        "General",
        "Entertainment",
        "Sports",
        "Business",
        "Technology",
        "Science",
        "Health")

    private val categoryTitles = arrayListOf(
        "Latest News",
        "General",
        "Entertainment",
        "Sports",
        "Business",
        "Technology",
        "Science",
        "Health")
    override fun getItem(position: Int): Fragment {
        return NewsListFragment.newInstance(categories[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryTitles[position]
    }

    override fun getCount(): Int {
        return 8
    }

}