package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

object EmptyScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = EmptyJob()
}