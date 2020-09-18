package com.abhi.modernnewsapp.news.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.abhi.modernnewsapp.R
import com.abhi.modernnewsapp.core.extensions.loadImageUrl
import com.abhi.modernnewsapp.databinding.FullWidthNewsItemViewBinding
import com.abhi.modernnewsapp.databinding.HalfWidthNewsItemLayoutBinding
import com.abhi.modernnewsapp.news.constants.NewsConstants
import com.abhi.modernnewsapp.news.data.storage.NewsArticleModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class NewsListingAdapter(
    private val context: Context?,
    val itemListener: (Int, Int) -> Unit
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
            is FullWidthViewHolder -> holder.bindData(position)
            is HalfWidthViewHolder -> holder.bindData(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position%5 == 0) R.layout.full_width_news_item_view
        else R.layout.half_width_news_item_layout
    }

    fun submitList(newsList: List<NewsArticleModel>) {
        mNewsList = newsList
        notifyDataSetChanged()
    }

    inner class FullWidthViewHolder(private val binding: FullWidthNewsItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.bookmarkImageView.setOnClickListener {
               itemListener(adapterPosition, NewsConstants.BOOKMARK_NEWS_ITEM)
            }
            binding.parentNewsLayout.setOnClickListener {
                itemListener(adapterPosition, NewsConstants.OPEN_NEWS_DETAIL)
            }
        }


        fun bindData(
            position: Int
        ) {
            val model = mNewsList[position]
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))
            binding.thumbnailImage.loadImageUrl(model.urlToImage, requestOptions)
            binding.titleTV.text = model.title ?: "Default Title"
            if(model.isBookmarked) {
               /* binding.bookmarkImageView.setColorFilter(
                    ContextCompat.getColor(context!!, R.color.white),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )*/
                binding.bookmarkImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_bookmark_24))
                /*DrawableCompat.setTint(binding.bookmarkImageView.drawable,
                    ContextCompat.getColor(context!!, R.color.light_grey))*/

            }else {
                /*DrawableCompat.setTint(binding.bookmarkImageView.drawable,
                    ContextCompat.getColor(context!!, R.color.grey_text))*/
                binding.bookmarkImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_bookmark_border_24))

                //binding.bookmarkImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_bookmark_border_24))
            }

        }

    }

    inner class HalfWidthViewHolder(private val binding: HalfWidthNewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.bookmarkImageView.setOnClickListener {
                itemListener(adapterPosition, NewsConstants.BOOKMARK_NEWS_ITEM)
            }
            binding.parentNewsLayout.setOnClickListener {
                itemListener(adapterPosition, NewsConstants.OPEN_NEWS_DETAIL)
            }
        }

        fun bindData(
            position: Int
        ) {
            val model = mNewsList[position]
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))
            binding.thumbnailImage.loadImageUrl(model.urlToImage, requestOptions)
            binding.titletextview.text = model.title ?: "Default Title"
            if(model.isBookmarked) {
                binding.bookmarkImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_bookmark_24))
            } else {
                binding.bookmarkImageView.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_bookmark_border_24))
            }
        }
    }
}