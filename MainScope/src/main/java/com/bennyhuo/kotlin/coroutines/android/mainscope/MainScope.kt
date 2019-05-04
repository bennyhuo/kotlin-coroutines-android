package com.bennyhuo.kotlin.coroutines.android.mainscope

import com.bennyhuo.kotlin.coroutines.android.mainscope.job.EmptyJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface MainScope: CoroutineScope

internal class MainScopeImpl: MainScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.Main
}

internal object EmptyScope : MainScope {
    override val coroutineContext: CoroutineContext = EmptyJob()
}