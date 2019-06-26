package com.bennyhuo.kotlin.coroutines.android.sample

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.utils.Logcat
import kotlinx.android.synthetic.main.dialog_main.*
import kotlinx.coroutines.delay

class MyDialog(context: Context) : Dialog(context), BasicScoped {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_main)

        ok.onClick {
            Logcat.debug("Run in mainScope")
            delay(1000)
            Logcat.debug("Run in mainScope again.")
        }

        cancel.onClick {
            dismiss()
        }
    }
}