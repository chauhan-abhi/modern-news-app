package com.abhi.modernnewsapp.core.uistate

sealed class ViewState<T> {

    data class Success<T>(
            val data: T
    ): ViewState<T>()

    class Loading<T>: ViewState<T>()

    data class Error<T>(
            val message: String
    ) : ViewState<T>()

    companion object {
        fun <T> success(data: T): ViewState<T> = Success(data)

        fun <T> loading(): ViewState<T> = Loading()

        fun <T> error(message: String): ViewState<T> = Error(message)
    }
}