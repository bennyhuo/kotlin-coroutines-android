package com.bennyhuo.kotlin.coroutines.android.mainscope.utils

import android.util.Log
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope

object Logcat {
    private const val TAG = "MainScope"

    fun debug(log: Any?) = MainScope.isDebug.whenTrue{ Log.d(TAG, log.toString()) }

    fun warn(log: Any?) = MainScope.isDebug.whenTrue{ Log.w(TAG, log.toString()) }

    fun error(log: Any?) = MainScope.isDebug.whenTrue{ Log.e(TAG, log.toString()) }

    private fun Boolean.whenTrue(block: () -> Unit){
        if(this){
            block()
        }
    }
}