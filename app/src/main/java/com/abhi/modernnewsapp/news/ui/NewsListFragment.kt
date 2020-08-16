package com.abhi.modernnewsapp.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.extensions.observeNotNull
import com.abhi.modernnewsapp.core.uistate.ViewState
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cateogory = arguments?.getString("category") ?: ""
        hitServiceButton.setOnClickListener {
            newsViewModel.getNewsForCategory(cateogory).observeNotNull(this) { state ->
                when(state) {
                    is ViewState.Success -> Log.d("RES: ", state.data.toString())
                    is ViewState.Loading -> Log.d("RES: ", "Loading..")
                    is ViewState.Error   -> Log.d("RES: ", "Error...")
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}