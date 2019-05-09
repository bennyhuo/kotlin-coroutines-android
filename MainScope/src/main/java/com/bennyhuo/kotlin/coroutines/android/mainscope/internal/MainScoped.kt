package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.app.Activity
import android.os.Looper
import android.support.v4.app.Fragment
import com.bennyhuo.kotlin.coroutines.android.mainscope.EmptyScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScopeImpl
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat
import kotlinx.coroutines.cancel
import java.util.*

interface MainScoped {
    companion object {
        internal val scopeMap = IdentityHashMap<MainScoped, MainScope>()
    }

    val mainScope: MainScope
        get() {
            if(!MainScope.isSetUp){
                throw IllegalStateException("MainScope has not been set up yet! Call `MainScope.setUp(application)` once your customized Application created.")
            }
            if(Thread.currentThread() != Looper.getMainLooper().thread){
                throw IllegalAccessException("MainScope must be accessed from the UI main thread.")
            }
            return (scopeMap[this]) ?: run {
                if(isDestroyed()){
                    Logcat.debug("Access MainScope when scoped instance:$this is FINISHING. EmptyScope will be returned.")
                    EmptyScope
                } else {
                    Logcat.warn("Create MainScope for scoped instance: $this")
                    MainScopeImpl().also { scopeMap[this] = it }
                }
            }
        }
}

private fun MainScoped.isDestroyed(): Boolean {
    return when{
        this is Activity ->{
            this.isFinishing
        }
        MainScope.isFragmentSupported && this is Fragment ->{
            this.activity?.isFinishing?: true || this.view == null
        }
        else ->{
            throw IllegalAccessException("An Activity or android.support.v4.app.Fragment is needed!")
        }
    }
}

inline fun <T> MainScoped.withMainScope(block: MainScope.() -> T) = with(mainScope, block)

internal fun MainScoped.onMainScopeCreate() {
    //won't create immediately.
}

internal fun MainScoped.onMainScopeDestroy() {
    MainScoped.scopeMap.remove(this)?.cancel()
}