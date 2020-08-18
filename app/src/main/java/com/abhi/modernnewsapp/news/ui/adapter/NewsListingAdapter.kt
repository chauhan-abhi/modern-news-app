package com.abhi.modernnewsapp.news.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.databinding.FullWidthNewsItemViewBinding
import com.abhi.modernnewsapp.databinding.HalfWidthNewsItemLayoutBinding
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class NewsListingAdapter(
    private val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mNewsList: List<NewsArticleModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        return when (viewType) {
            R.layout.full_width_news_item_view -> {
                val binding = DataBindingUtil.inflate<FullWidthNewsItemViewBinding>(
                    layoutInflater, viewType, parent, false
                )
                FullWidthViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<HalfWidthNewsItemLayoutBinding>(
                    layoutInflater, viewType, parent, false
                )
                HalfWidthViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = mNewsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FullWidthViewHolder -> holder.bindData(holder, position)
            is HalfWidthViewHolder -> holder.bindData(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.full_width_news_item_view
        else R.layout.half_width_news_item_layout
    }

    fun submitList(newsList: List<NewsArticleModel>) {
        mNewsList = newsList
        notifyDataSetChanged()
    }

    inner class FullWidthViewHolder(val binding: FullWidthNewsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            holder: FullWidthViewHolder,
            position: Int
        ) {
            val model = mNewsList[position]
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))
            context?.let {
                Glide.with(it)
                    .load(model.urlToImage)
                    .apply(requestOptions)
                    .into(binding.thumbnailImage)
            }
            binding.titleTV.text = model.title ?: "Default Title"
        }

    }

    inner class HalfWidthViewHolder(val binding: HalfWidthNewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(
            holder: HalfWidthViewHolder,
            position: Int
        ) {
            val model = mNewsList[position]
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))
            context?.let {
                Glide.with(it)
                    .load(model.urlToImage)
                    .apply(requestOptions)
                    .into(binding.thumbnailImage)
            }
            binding.titletextview.text = model.title ?: "Default Title"
        }
    }
}