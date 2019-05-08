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
import kotlinx.coroutines.CoroutineStart.ATOMIC
import kotlinx.coroutines.CoroutineStart.UNDISPATCHED

class MainActivity : AppCompatActivity(), AppCompatScoped, RecyclerViewScoped, DesignScoped {

    override fun onCreate(savedInstanceState: Bundle?) {
        log("Before super.onCreate, mainScope:$mainScope is Active: ${mainScope.isActive}")
        super.onCreate(savedInstanceState)
        log("After super.onCreate, mainScope:$mainScope is Active: ${mainScope.isActive}")

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
                log("Now we'll start an async block.")
                val result = async(Dispatchers.IO) {
                    delay(100)
                    "HelloWorld"
                }
                log("Got result: $result")
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
        log("Before super.onDestroy, mainScope:$mainScope is Active: ${mainScope.isActive}")

        // mainScope is cancelled in super.onDestroy, and after that we will return a single instance of EmptyScope
        // which will not dispatch any coroutines.
        super.onDestroy()
        log("After super.onDestroy, mainScope:$mainScope is Active: ${mainScope.isActive}")
        withMainScope {
            launch {
                val job = coroutineContext[Job]
                log("This may be dispatched according to the start mode: DEFAULT. isActive: ${job?.isActive}, isCancelled: ${job?.isCancelled}")
                //Scope is cancelled, so any cancellable suspend functions will respond to it.
                delay(100)
                log("[DEFAULT][Empty]Never gonna happen.")
            }

            launch(start = ATOMIC) {
                val job = coroutineContext[Job]
                log("This may be dispatched according to the start mode: ATOMIC. isActive: ${job?.isActive}, isCancelled: ${job?.isCancelled}")
                //Scope is cancelled, so any cancellable suspend functions will respond to it.
                delay(100)
                log("[ATOMIC][DEFAULT]Never gonna happen.")
            }

            launch(start = UNDISPATCHED) {
                val job = coroutineContext[Job]
                log("This may be dispatched according to the start mode: UNDISPATCHED. isActive: ${job?.isActive}, isCancelled: ${job?.isCancelled}")
                //Scope is cancelled, so any cancellable suspend functions will respond to it.
                delay(100)
                log("[UNDISPATCHED][DEFAULT]Never gonna happen.")
            }

            launch(Dispatchers.Default) {
                val job = coroutineContext[Job]
                log("This may be dispatched according to the start mode: DEFAULT, using Dispatcher: Default. isActive: ${job?.isActive}, isCancelled: ${job?.isCancelled}")
                //Scope is cancelled, so any cancellable suspend functions will respond to it.
                delay(100)
                log("[DEFAULT][DEFAULT]Never gonna happen.")
            }
        }
    }

    fun log(msg: Any?) = Log.d("Coroutine", "$msg")
}
