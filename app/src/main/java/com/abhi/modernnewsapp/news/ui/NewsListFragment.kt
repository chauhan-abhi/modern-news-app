package com.abhi.modernnewsapp.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.extensions.observeNotNull
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.ui.adapter.NewsListingAdapter
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_list.*

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()
    private var cateogory: String = ""

    companion object {
        fun newInstance(category: String): NewsListFragment {
            val fragment = NewsListFragment()
            fragment.arguments = Bundle().apply {
                putString("category", category)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVariables()
        val newsListAdapter = NewsListingAdapter(context)
        rvNewsListing.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.news_divider)?.let {
                divider.setDrawable(
                    it
                )
            }
            addItemDecoration(divider)
        }
        newsViewModel.getNewsForCategory(cateogory).observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    rvProgress.visibility = View.GONE
                    newsListAdapter.submitList(state.data)
                }
                is ViewState.Loading -> rvProgress.visibility = View.VISIBLE
                is ViewState.Error -> Log.d("RES: ", "Error...")
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initVariables() {
        cateogory = arguments?.getString("category") ?: ""
    }
}