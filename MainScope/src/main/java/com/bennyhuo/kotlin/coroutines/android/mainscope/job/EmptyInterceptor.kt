package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import android.util.Log
import com.bennyhuo.kotlin.coroutines.android.mainscope.internal.TAG
import kotlin.coroutines.*

internal object EmptyInterceptor: ContinuationInterceptor, AbstractCoroutineContextElement(ContinuationInterceptor) {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return EmptyContinuation as Continuation<T>
    }

    private object EmptyContinuation: Continuation<Any>{
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<Any>) {
            Log.w(TAG, "Intercepted coroutine. won't resume.")
        }
    }
}