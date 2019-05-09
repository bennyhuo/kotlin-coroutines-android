package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.app.Application
import com.bennyhuo.kotlin.coroutines.android.mainscope.internal.ActivityLifecycleCallbackImpl
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyInterceptor
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyJob
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.ImmutableCoroutineContext
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface MainScope: CoroutineScope{
    companion object{
        internal var isSetUp = false
        internal var isDebug = false

        val isFragmentSupported by lazy {
            try {
                Class.forName("android.support.v4.app.Fragment")
                Logcat.debug("Fragment enabled.")
                true
            }catch (e: ClassNotFoundException){
                Logcat.debug("Fragment disabled.")
                Logcat.error(e)
                false
            }
        }

        fun setUp(application: Application): Companion {
            application.registerActivityLifecycleCallbacks(ActivityLifecycleCallbackImpl)
            isSetUp = true
            return this
        }

        fun enableDebug(){
            isDebug = true
        }
    }
}

internal class MainScopeImpl: MainScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main
}

internal object EmptyScope : MainScope {
    override val coroutineContext: CoroutineContext = ImmutableCoroutineContext(EmptyJob() + EmptyInterceptor)
}