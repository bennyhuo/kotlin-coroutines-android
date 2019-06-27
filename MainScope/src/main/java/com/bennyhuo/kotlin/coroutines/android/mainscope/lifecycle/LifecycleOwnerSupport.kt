package com.bennyhuo.kotlin.coroutines.android.mainscope.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import com.bennyhuo.kotlin.coroutines.android.mainscope.EmptyScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.exception.UnsupportedTypeException
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.MainScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat
import java.lang.ref.WeakReference

private val lifecycleOwnerField by lazy {
    try {
        LifecycleRegistry::class.java.getDeclaredField("mLifecycleOwner").also { it.isAccessible = true }
    } catch (e: Exception) {
        Logcat.warn("Cannot retrieve property: mLifecycleOwner from LifecycleRegistry.")
        null
    }
}

val Lifecycle.mainScope: MainScope
    get() {
        return if(this is LifecycleRegistry) mainScope else {
            Logcat.warn("Only lifecycles of Activity or Fragment are supported.")
            throw UnsupportedTypeException(this::class.java, "LifecycleRegistry")
        }
    }

val LifecycleRegistry.mainScope: MainScope
    get() {
        return ((lifecycleOwnerField?.get(this) as? WeakReference<*>)?.get() as? MainScoped)?.mainScope ?: EmptyScope
    }