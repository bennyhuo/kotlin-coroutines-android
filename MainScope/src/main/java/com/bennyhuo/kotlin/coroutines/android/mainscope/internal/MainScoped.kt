package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.app.Activity
import android.os.Looper
import android.util.Log
import com.bennyhuo.kotlin.coroutines.android.mainscope.EmptyScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScopeImpl
import kotlinx.coroutines.cancel
import java.util.*

internal const val TAG = "MainScope"

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
            val activity = this as Activity
            return (scopeMap[activity]) ?: run {
                if(activity.isFinishing){
                    Log.w(TAG, "Access MainScope when activity:$activity is FINISHING. EmptyScope will be returned.")
                    EmptyScope
                } else {
                    Log.d(TAG, "Create MainScope for activity: $activity")
                    MainScopeImpl().also { scopeMap[activity] = it }
                }
            }
        }
}

inline fun <T> MainScoped.withMainScope(block: MainScope.() -> T) = with(mainScope, block)

internal fun MainScoped.onCreate() {
    //won't create immediately.
}

internal fun MainScoped.onDestroy() {
    MainScoped.scopeMap.remove(this as Activity)?.cancel()
}