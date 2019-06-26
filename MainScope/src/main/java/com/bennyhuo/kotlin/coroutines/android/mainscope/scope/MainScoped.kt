package com.bennyhuo.kotlin.coroutines.android.mainscope.scope

import android.app.Activity
import android.app.Dialog
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.View
import android.view.View.OnAttachStateChangeListener
import com.bennyhuo.kotlin.coroutines.android.mainscope.EmptyScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScopeImpl
import com.bennyhuo.kotlin.coroutines.android.mainscope.exception.UnsupportedTypeException
import com.bennyhuo.kotlin.coroutines.android.mainscope.exception.UnsupportedVersionException
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
            return ((scopeMap[this]) ?: run {
                if(isDestroyed()){
                    Logcat.debug("Access MainScope when scoped instance:$this is FINISHING. EmptyScope will be returned.")
                    EmptyScope
                } else {
                    Logcat.warn("Create MainScope for scoped instance: $this")
                    if(this is Dialog){
                        installDestroyListener()
                    }
                    MainScopeImpl().also { scopeMap[this] = it }
                }
            }).also {
                Logcat.debug(scopeMap)
            }
        }

    private fun Dialog.installDestroyListener(){
        //install destroy listener for dialog.
        val window = this.window
        if(window == null){
            throw IllegalStateException("Dialog window is null while showing, should not happen.")
        } else {
            Logcat.debug("Dialog DecorView: ${window.decorView.hashCode()}")
            window.decorView.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
                override fun onViewDetachedFromWindow(v: View) {
                    Logcat.debug("onViewDetachedFromWindow: ${v.hashCode()}")
                    (this@installDestroyListener as MainScoped).onMainScopeDestroy()
                    v.removeOnAttachStateChangeListener(this)
                }
                override fun onViewAttachedToWindow(v: View) = Unit
            })
        }
    }
}

private fun MainScoped.isDestroyed(): Boolean {
    return when{
        this is Activity ->{
            this.isFinishing
        }
        this is Dialog-> {
            !this.isShowing
        }
        MainScope.isFragmentSupported && this is Fragment ->{
            this.activity?.isFinishing?: true || this.isRemoving ||this.view == null
        }
        else ->{
            val fragmentClass = try {
                Class.forName("android.support.v4.app.Fragment")
            } catch (e: Exception) {
                null
            }
            fragmentClass?.let {
                if(it.isAssignableFrom(this.javaClass)){
                    throw UnsupportedVersionException("com.android.support:support-fragment", "<25.1.0")
                }
            }
            throw UnsupportedTypeException(this.javaClass, "android.app.Activity", "android.support.v4.app.Fragment")
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