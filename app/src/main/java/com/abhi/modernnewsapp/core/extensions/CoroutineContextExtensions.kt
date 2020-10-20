package com.abhi.modernnewsapp.core.extensions

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun CoroutineScope.launchWithCatchError(context: CoroutineContext = coroutineContext,
                                        block: suspend CoroutineScope.() -> Unit,
                                        errorBlock: suspend (Throwable) -> Unit) =
    launch (context) {
        try{
            block()
        } catch (t: Throwable){
            if (t is CancellationException) throw t
            else {
                try {
                    errorBlock(t)
                } catch (e: Throwable){

                }
            }
        }
    }

fun <T> CoroutineScope.asyncWithCatchError(context: CoroutineContext = coroutineContext,
                                           block: suspend CoroutineScope.() -> T,
                                           errorBlock: suspend (Throwable) -> T): Deferred<T?> {
    return async(context) {
        try {
            block()
        } catch (e: Exception) {
            try {
                errorBlock(e)
            } catch (e: Exception) {
                null
            }
        }
    }
}

