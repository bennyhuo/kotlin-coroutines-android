package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Activity
import android.app.Application
import android.os.Bundle

internal class ActivityLifecycleCallbackImpl : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as? Scoped)?.onCreate()
    }

    override fun onActivityDestroyed(activity: Activity) {
        (activity as? Scoped)?.onDestroy()
    }
}
