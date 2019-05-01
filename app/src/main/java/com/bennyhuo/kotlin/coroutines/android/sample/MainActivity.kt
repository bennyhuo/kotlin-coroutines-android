package com.bennyhuo.kotlin.coroutines.android.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bennyhuo.kotlin.coroutines.android.autodisposable.asAutoDisposable
import com.bennyhuo.kotlin.coroutines.android.mainscope.ScopedActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ScopedActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.onClickSuspend {
            log(1)
            delay(1000)
            log(2)
            textView.text = "Hello Coroutine!"
        }

        button.setOnClickListener {
            GlobalScope.launch {
                log(1)
                delay(1000)
                log(2)
            }.asAutoDisposable(it)
        }
    }

    fun log(msg: Any?) = Log.d("Coroutine", "$msg")
}
