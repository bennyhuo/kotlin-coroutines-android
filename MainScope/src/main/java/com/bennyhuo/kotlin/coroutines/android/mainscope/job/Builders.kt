package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.newCoroutineContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.launch as launchInternal


internal fun MainScope.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend MainScope.() -> Unit
): Job {
    val newContext = newCoroutineContext(context)
    val coroutine = StandaloneCoroutineCompat(newContext, true)
    coroutine.start(start, coroutine, block)
    return coroutine
}
