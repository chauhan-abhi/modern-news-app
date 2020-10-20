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
import com.abhi.modernnewsapp.core.extensions.gone
import com.abhi.modernnewsapp.core.extensions.inTransaction
import com.abhi.modernnewsapp.core.extensions.observeNotNull
import com.abhi.modernnewsapp.core.extensions.visible
import com.abhi.modernnewsapp.core.uistate.ViewState
import com.abhi.modernnewsapp.news.constants.NewsConstants
import com.abhi.modernnewsapp.news.ui.adapter.NewsListingAdapter
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import timber.log.Timber

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    // viewmodel store owner is Fragment
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
        initVariables()
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //mToolbar.title = cateogory
        val newsListAdapter = NewsListingAdapter(context) { position, action ->
            handleAction(position, action)
        }
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
        newsViewModel.getNewsForCategory(cateogory)
        newsViewModel.getNewsLiveData().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    rvProgress.gone()
                    newsListAdapter.submitList(state.data)
                }
                is ViewState.Loading -> rvProgress.visible()
                is ViewState.Error -> Log.d("RES: ", "Error...")
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleAction(position: Int, action: Int) {
        when(action) {
            NewsConstants.OPEN_NEWS_DETAIL -> openNewsDetail(position)
            NewsConstants.BOOKMARK_NEWS_ITEM -> bookmarkArticle(position)
        }
    }

    private fun bookmarkArticle(position: Int) {
        ((newsViewModel.getNewsLiveData()
            .value as ViewState.Success).data[position]).let {
            it.isBookmarked = !it.isBookmarked
            newsViewModel.bookMarkArticle(it)
        }
    }

    private fun openNewsDetail(position: Int) {
        Timber.e("Item clicked $position + $cateogory ==> ")
        Timber.e(((newsViewModel.getNewsLiveData().value as ViewState.Success).data[position]).title)
        val bundle = Bundle().apply {
            putSerializable("detail", (newsViewModel.getNewsLiveData().value as ViewState.Success).data[position])
        }
        val newsDetailFragment = NewsDetailFragment.getInstance(bundle)
        activity?.supportFragmentManager?.inTransaction {
            addToBackStack(this@NewsListFragment.javaClass.name)
            add(R.id.content_frame, newsDetailFragment)
        }
    }

    private fun initVariables() {
        cateogory = arguments?.getString("category") ?: ""
    }
}