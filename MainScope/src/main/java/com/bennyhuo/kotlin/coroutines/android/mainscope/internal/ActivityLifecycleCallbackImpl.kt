package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat

internal object ActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as? MainScoped)?.onMainScopeCreate()
        if (MainScope.isFragmentSupported) {
            (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(FragmentLifecycleCallbackImpl, true)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        (activity as? MainScoped)?.onMainScopeDestroy()
        if (MainScope.isFragmentSupported) {
            Logcat.debug("onActivityDestroyed")
            (activity as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                fragmentManager.unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbackImpl)
                //Fragments may not be destroyed, so cancel scope right now.
                fragmentManager.fragments.forEach { fragment ->
                    (fragment as? MainScoped)?.onMainScopeDestroy()
                }
            }
        }
    }
}
