package com.bennyhuo.kotlin.coroutines.android.sample

import android.app.Application
import com.bennyhuo.kotlin.coroutines.android.mainscope.Scoped

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Scoped.setUp(this)
    }
}