package com.bennyhuo.kotlin.coroutines.android.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import com.bennyhuo.kotlin.coroutines.android.mainscope.AppCompatScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.DesignScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.RecyclerViewScoped
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity(), AppCompatScoped, DesignScoped, RecyclerViewScoped {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.onClick {
            log(1)
            delay(1000)
            log(2)
            textView.text = "Hello Coroutine!"
        }

        val anotherButton = Button(this)
        parentView.addView(anotherButton, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        anotherButton.onClick {
                log(1)
                delay(1000)
                log(2)
        }
    }

    fun log(msg: Any?) = Log.d("Coroutine", "$msg")
}
