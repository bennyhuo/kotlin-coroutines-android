package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Application
import com.bennyhuo.kotlin.coroutines.android.mainscope.internal.ActivityLifecycleCallbackImpl
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface MainScope: CoroutineScope{
    companion object{
        fun setUp(application: Application){
            application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl())
        }
    }
}

internal class MainScopeImpl: MainScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main
}

internal object EmptyScope : MainScope {
    override val coroutineContext: CoroutineContext = EmptyJob()
}