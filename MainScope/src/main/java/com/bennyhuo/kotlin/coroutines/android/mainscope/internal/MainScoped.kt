package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.app.Activity
import android.os.Looper
import com.bennyhuo.kotlin.coroutines.android.mainscope.EmptyScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScopeImpl
import kotlinx.coroutines.cancel
import java.util.*

interface MainScoped {
    companion object {
        internal val scopeMap = IdentityHashMap<Activity, MainScope>()
    }

    val mainScope: MainScope
        get() {
            if(Thread.currentThread() != Looper.getMainLooper().thread){
                throw IllegalAccessException("MainScope")
            }
            return (scopeMap[this as Activity]) ?: EmptyScope
        }
}

inline fun <T> MainScoped.withMainScope(block: MainScope.() -> T) = with(mainScope, block)

internal fun MainScoped.onCreate() {
    val activity = this as Activity
    MainScoped.scopeMap[activity] ?: MainScopeImpl().also { MainScoped.scopeMap[activity] = it }
}

internal fun MainScoped.onDestroy() {
    MainScoped.scopeMap.remove(this as Activity)?.cancel()
}