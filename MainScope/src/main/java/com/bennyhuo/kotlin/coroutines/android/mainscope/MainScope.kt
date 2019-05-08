package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Application
import com.bennyhuo.kotlin.coroutines.android.mainscope.internal.ActivityLifecycleCallbackImpl
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyInterceptor
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyJob
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.ImmutableCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface MainScope: CoroutineScope{
    companion object{
        internal var isSetUp = false

        fun setUp(application: Application){
            application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
            isSetUp = true
        }
    }
}

internal class MainScopeImpl: MainScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main
}

internal object EmptyScope : MainScope {
    override val coroutineContext: CoroutineContext = ImmutableCoroutineContext(EmptyJob() + EmptyInterceptor)
}