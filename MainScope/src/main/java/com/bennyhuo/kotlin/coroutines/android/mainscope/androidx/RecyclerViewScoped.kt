package com.bennyhuo.kotlin.coroutines.android.mainscope.androidx

import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.launch
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped

interface RecyclerViewScoped: BasicScoped {

    fun androidx.recyclerview.widget.RecyclerView.onChildAttachStateChangeListener(
            init: __RecyclerView_OnChildAttachStateChangeListener.() -> Unit
    ) {
        val listener = __RecyclerView_OnChildAttachStateChangeListener(mainScope)
        listener.init()
        addOnChildAttachStateChangeListener(listener)
    }

    class __RecyclerView_OnChildAttachStateChangeListener(private val mainScope: MainScope) : androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener {

        private var _onChildViewAttachedToWindow: (suspend MainScope.(android.view.View) -> Unit)? = null


        override fun onChildViewAttachedToWindow(view: android.view.View) {
            val handler = _onChildViewAttachedToWindow ?: return
            mainScope.launch {
                handler(view)
            }
        }

        fun onChildViewAttachedToWindow(
                listener: suspend MainScope.(android.view.View) -> Unit
        ) {
            _onChildViewAttachedToWindow = listener
        }

        private var _onChildViewDetachedFromWindow: (suspend MainScope.(android.view.View) -> Unit)? = null


        override fun onChildViewDetachedFromWindow(view: android.view.View) {
            val handler = _onChildViewDetachedFromWindow ?: return
            mainScope.launch {
                handler(view)
            }
        }

        fun onChildViewDetachedFromWindow(
                listener: suspend MainScope.(android.view.View) -> Unit
        ) {
            _onChildViewDetachedFromWindow = listener
        }

    }fun androidx.recyclerview.widget.RecyclerView.onItemTouchListener(
            init: __RecyclerView_OnItemTouchListener.() -> Unit
    ) {
        val listener = __RecyclerView_OnItemTouchListener(mainScope)
        listener.init()
        addOnItemTouchListener(listener)
    }

    class __RecyclerView_OnItemTouchListener(private val mainScope: MainScope) : androidx.recyclerview.widget.RecyclerView.OnItemTouchListener {

        private var _onInterceptTouchEvent: (suspend MainScope.(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent) -> Boolean)? = null
        private var _onInterceptTouchEvent_returnValue: Boolean = false

        override fun onInterceptTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: android.view.MotionEvent) : Boolean {
            val returnValue = _onInterceptTouchEvent_returnValue
            val handler = _onInterceptTouchEvent ?: return returnValue
            mainScope.launch {
                handler(rv, e)
            }
            return returnValue
        }

        fun onInterceptTouchEvent(
                returnValue: Boolean = false,
                listener: suspend MainScope.(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent) -> Boolean
        ) {
            _onInterceptTouchEvent = listener
            _onInterceptTouchEvent_returnValue = returnValue
        }

        private var _onTouchEvent: (suspend MainScope.(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent) -> Unit)? = null


        override fun onTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: android.view.MotionEvent) {
            val handler = _onTouchEvent ?: return
            mainScope.launch {
                handler(rv, e)
            }
        }

        fun onTouchEvent(
                listener: suspend MainScope.(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent) -> Unit
        ) {
            _onTouchEvent = listener
        }

        private var _onRequestDisallowInterceptTouchEvent: (suspend MainScope.(Boolean) -> Unit)? = null


        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            val handler = _onRequestDisallowInterceptTouchEvent ?: return
            mainScope.launch {
                handler(disallowIntercept)
            }
        }

        fun onRequestDisallowInterceptTouchEvent(
                listener: suspend MainScope.(Boolean) -> Unit
        ) {
            _onRequestDisallowInterceptTouchEvent = listener
        }

    }
}