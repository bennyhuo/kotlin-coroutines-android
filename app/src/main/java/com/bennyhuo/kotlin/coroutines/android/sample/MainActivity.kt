package com.bennyhuo.kotlin.coroutines.android.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import com.bennyhuo.kotlin.coroutines.android.autodisposable.asAutoDisposable
import com.bennyhuo.kotlin.coroutines.android.mainscope.AppCompatScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.DesignScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.RecyclerViewScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.internal.withMainScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), AppCompatScoped, RecyclerViewScoped, DesignScoped {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScope.launch {
            log("Hey!")
        }

        button.onClick {
            log(1)
            delay(1000)
            log(2)
            textView.text = "Hello Coroutine!"
        }

        withMainScope {
            launch {

            }

            async(Dispatchers.IO) {

            }
        }

        val anotherButton = Button(this)
        parentView.addView(anotherButton, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        anotherButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                log(1)
                parentView.removeView(anotherButton)
                delay(1000)
                log(2)
            }.asAutoDisposable(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        withMainScope {
            launch {  }
        }
    }

    fun log(msg: Any?) = Log.d("Coroutine", "$msg")
}
