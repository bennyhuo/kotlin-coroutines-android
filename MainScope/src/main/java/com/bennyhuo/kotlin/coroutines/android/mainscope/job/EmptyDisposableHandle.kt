package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import kotlinx.coroutines.DisposableHandle

object EmptyDisposableHandle: DisposableHandle {
    override fun dispose() = Unit
}