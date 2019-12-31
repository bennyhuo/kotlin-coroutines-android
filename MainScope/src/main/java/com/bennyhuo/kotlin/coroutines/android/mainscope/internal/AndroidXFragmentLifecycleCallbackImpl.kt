package com.bennyhuo.kotlin.coroutines.android.mainscope.internal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.MainScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.onMainScopeCreate
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.onMainScopeDestroy
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat

internal object AndroidXFragmentLifecycleCallbackImpl: FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        (f as? MainScoped)?.onMainScopeCreate()
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        Logcat.debug("onFragmentViewDestroyed")
        (f as? MainScoped)?.onMainScopeDestroy()
    }
}