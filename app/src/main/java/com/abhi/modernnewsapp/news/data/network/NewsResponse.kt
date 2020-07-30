package com.abhi.modernnewsapp.news.data.network

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults : Int = -1,

    @SerializedName("articles")
    val articles : List<ArticleModel> = emptyList()
)