package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Activity
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import java.util.*

interface Scoped {
    companion object {
        internal val scopeMap = IdentityHashMap<Activity, MainScope>()

        fun setUp(application: Application){
            application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
        }
    }

    val scope: MainScope
        get() = (scopeMap[this as Activity]) ?: EmptyScope

    fun <T> withScope(block: CoroutineScope.() -> T) = with(scope, block)
}

internal fun Scoped.onCreate() {
    val activity = this as Activity
    Scoped.scopeMap[activity] ?: MainScopeImpl().also { Scoped.scopeMap[activity] = it }
}

internal fun Scoped.onDestroy() {
    Scoped.scopeMap.remove(this as Activity)?.cancel()
}