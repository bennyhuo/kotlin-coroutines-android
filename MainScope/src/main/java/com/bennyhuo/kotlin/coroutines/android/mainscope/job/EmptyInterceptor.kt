package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat
import kotlin.coroutines.*

internal object EmptyInterceptor: ContinuationInterceptor, AbstractCoroutineContextElement(ContinuationInterceptor) {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return EmptyContinuation as Continuation<T>
    }

    private object EmptyContinuation: Continuation<Any>{
        override val context: CoroutineContext = EmptyCoroutineContext

        override fun resumeWith(result: Result<Any>) {
            Logcat.warn("Intercepted coroutine. won't resume.")
        }
    }
}