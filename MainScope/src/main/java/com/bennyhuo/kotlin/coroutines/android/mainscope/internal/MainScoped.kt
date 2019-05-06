package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.app.Activity
import android.os.Looper
import android.util.Log
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
            if(!MainScope.isSetUp){
                throw IllegalStateException("MainScope has not been set up yet! Call `MainScope.setUp(application)` once your customized Application created.")
            }
            if(Thread.currentThread() != Looper.getMainLooper().thread){
                throw IllegalAccessException("MainScope must be accessed from the UI main thread.")
            }
            return (scopeMap[this as Activity]) ?: EmptyScope.also {
                Log.w("MainScope", "Access MainScope when Activity is not created or destroyed.")
            }
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