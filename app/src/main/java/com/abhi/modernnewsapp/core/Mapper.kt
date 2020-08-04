package com.abhi.modernnewsapp.core

interface Mapper<Cache, Remote> {
    fun Cache.toRemote(): Remote
    fun Remote.toCache(category: String): Cache
    fun List<Cache>.toRemote() : List<Remote> = this.map { it.toRemote()}
    fun List<Remote>.toCache(category: String) : List<Cache>  = this.map { it.toCache(category) }
}