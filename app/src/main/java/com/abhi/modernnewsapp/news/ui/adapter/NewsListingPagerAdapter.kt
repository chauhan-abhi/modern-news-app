package com.abhi.modernnewsapp.news.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhi.modernnewsapp.news.ui.NewsListFragment

class NewsListingPagerAdapter(val fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    val categories = arrayListOf(
        "",
        "General",
        "Entertainment",
        "Sports",
        "Business",
        "Technology",
        "Science",
        "Health")

    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return NewsListFragment.newInstance(categories[position])
    }

    /*public fun getTabView(position: Int): View {
        val view = LayoutInflater.from(fragment).inflate(R.layout.custom_tab, null)

    }*/
}