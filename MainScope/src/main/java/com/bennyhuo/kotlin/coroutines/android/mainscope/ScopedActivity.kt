package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface ScopedActivity: MainScope {
    fun View.onClickSuspend(handler: suspend CoroutineScope.(v: View) -> Unit) {
        setOnClickListener { v ->
            scope.launch {
                handler(v)
            }
        }
    }

}
