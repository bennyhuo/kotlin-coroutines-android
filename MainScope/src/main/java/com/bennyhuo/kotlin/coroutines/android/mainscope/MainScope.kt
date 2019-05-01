package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Activity
import android.app.Application
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import java.util.*

interface MainScope {
    companion object {
        internal val scopeMap = IdentityHashMap<Activity, CoroutineScope>()

        fun setUp(application: Application){
            application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
        }
    }

    val scope: CoroutineScope
        get() = (scopeMap[this as Activity]) ?: EmptyScope

    fun <T> withScope(block: CoroutineScope.() -> T) = with(scope, block)
}

internal fun MainScope.onCreate() {
    val activity = this as Activity
    MainScope.scopeMap[activity] ?: MainScope().also { MainScope.scopeMap[activity] = it }
}

internal fun MainScope.onDestroy() {
    MainScope.scopeMap.remove(this as Activity)?.cancel()
}