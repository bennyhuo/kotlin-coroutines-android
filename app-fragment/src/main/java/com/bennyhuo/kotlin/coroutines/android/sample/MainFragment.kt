package com.bennyhuo.kotlin.coroutines.android.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.withMainScope
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.*

class MainFragment : Fragment(), BasicScoped {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mainScope.launch {
//            log("Hey!")
//        }

        button.onClick {
            log(1)
            delay(1000)
            log(2)
            textView.text = "Hello Coroutine in Fragment!"
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
    }

    override fun onDestroyView() {
        //log("Before super.onDestroyView, mainScope:$mainScope is Active: ${mainScope.isActive}")
        //MainScope is still valid if activity is not finishing.
        super.onDestroyView()
        //MainScope is gonna destroyed right after this method call if created before .
        log("After super.onDestroyView, mainScope:$mainScope is Active: ${mainScope.isActive}")
    }

    override fun onDestroy() {
        log("Before super.onDestroy, mainScope:$mainScope is Active: ${mainScope.isActive}")
        super.onDestroy()
        log("After super.onDestroy, mainScope:$mainScope is Active: ${mainScope.isActive}")
    }
}