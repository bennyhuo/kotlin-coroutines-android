package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat

internal object FragmentLifecycleCallbackImpl: FragmentLifecycleCallbacks() {
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        (f as? MainScoped)?.onMainScopeCreate()
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        Logcat.debug("onFragmentViewDestroyed")
        (f as? MainScoped)?.onMainScopeDestroy()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        Logcat.debug("onFragmentDestroyed")
    }
}