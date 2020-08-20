package com.abhi.modernnewsapp.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.extensions.loadImageUrl
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import com.abhi.modernnewsapp.news.viewmodel.NewsViewModel
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.*

@AndroidEntryPoint
class NewsDetailFragment: Fragment() {

    companion object {
        fun getInstance(bundle: Bundle) : NewsDetailFragment{
            val fragment = NewsDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var newsArticleModel: NewsArticleModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initVariables()
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    private fun initVariables() {
        newsArticleModel = arguments?.getSerializable("detail") as NewsArticleModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop())
        newsArticleModel.run {
            news_image_view.loadImageUrl(urlToImage, requestOptions)
            news_title_tv.text = title
            news_description_tv.text = description
        }


    }
}