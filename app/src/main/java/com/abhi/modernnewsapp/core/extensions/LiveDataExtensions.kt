package com.abhi.modernnewsapp.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observeNotNull(
        owner: LifecycleOwner,
        crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}