package com.bennyhuo.kotlin.coroutines.android.mainscope.androidx

import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.launch
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped

interface AppCompatScoped: BasicScoped {
    fun androidx.appcompat.widget.ActionMenuView.onMenuItemClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(item: android.view.MenuItem?) -> Unit
    ) {
        setOnMenuItemClickListener { item ->
            mainScope.launch {
                handler(item)
            }
            returnValue
        }
    }

    fun androidx.appcompat.widget.ActivityChooserView.onDismiss(
            handler: suspend MainScope.() -> Unit
    ) {
        setOnDismissListener {  ->
            mainScope.launch(block = handler)
        }
    }

    fun androidx.appcompat.widget.FitWindowsFrameLayout.onFitSystemWindows(
            handler: suspend MainScope.(insets: android.graphics.Rect?) -> Unit
    ) {
        setOnFitSystemWindowsListener { insets ->
            mainScope.launch {
                handler(insets)
            }
        }
    }

    fun androidx.appcompat.widget.SearchView.onClose(
            returnValue: Boolean = false,
            handler: suspend MainScope.() -> Unit
    ) {
        setOnCloseListener {  ->
            mainScope.launch(block = handler)
            returnValue
        }
    }

    fun androidx.appcompat.widget.SearchView.onQueryTextFocusChange(
            handler: suspend MainScope.(v: android.view.View, hasFocus: Boolean) -> Unit
    ) {
        setOnQueryTextFocusChangeListener { v, hasFocus ->
            mainScope.launch {
                handler(v, hasFocus)
            }
        }
    }

    fun androidx.appcompat.widget.SearchView.onQueryTextListener(
            init: __SearchView_OnQueryTextListener.() -> Unit
    ) {
        val listener = __SearchView_OnQueryTextListener(mainScope)
        listener.init()
        setOnQueryTextListener(listener)
    }

    class __SearchView_OnQueryTextListener(private val mainScope: MainScope) : androidx.appcompat.widget.SearchView.OnQueryTextListener {

        private var _onQueryTextSubmit: (suspend MainScope.(String?) -> Boolean)? = null
        private var _onQueryTextSubmit_returnValue: Boolean = false

        override fun onQueryTextSubmit(query: String?) : Boolean {
            val returnValue = _onQueryTextSubmit_returnValue
            val handler = _onQueryTextSubmit ?: return returnValue
            mainScope.launch {
                handler(query)
            }
            return returnValue
        }

        fun onQueryTextSubmit(
                returnValue: Boolean = false,
                listener: suspend MainScope.(String?) -> Boolean
        ) {
            _onQueryTextSubmit = listener
            _onQueryTextSubmit_returnValue = returnValue
        }

        private var _onQueryTextChange: (suspend MainScope.(String?) -> Boolean)? = null
        private var _onQueryTextChange_returnValue: Boolean = false

        override fun onQueryTextChange(newText: String?) : Boolean {
            val returnValue = _onQueryTextChange_returnValue
            val handler = _onQueryTextChange ?: return returnValue
            mainScope.launch {
                handler(newText)
            }
            return returnValue
        }

        fun onQueryTextChange(
                returnValue: Boolean = false,
                listener: suspend MainScope.(String?) -> Boolean
        ) {
            _onQueryTextChange = listener
            _onQueryTextChange_returnValue = returnValue
        }

    }fun androidx.appcompat.widget.SearchView.onSearchClick(
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnSearchClickListener { v ->
            mainScope.launch {
                handler(v)
            }
        }
    }

    fun androidx.appcompat.widget.SearchView.onSuggestionListener(
            init: __SearchView_OnSuggestionListener.() -> Unit
    ) {
        val listener = __SearchView_OnSuggestionListener(mainScope)
        listener.init()
        setOnSuggestionListener(listener)
    }

    class __SearchView_OnSuggestionListener(private val mainScope: MainScope) : androidx.appcompat.widget.SearchView.OnSuggestionListener {

        private var _onSuggestionSelect: (suspend MainScope.(Int) -> Boolean)? = null
        private var _onSuggestionSelect_returnValue: Boolean = false

        override fun onSuggestionSelect(position: Int) : Boolean {
            val returnValue = _onSuggestionSelect_returnValue
            val handler = _onSuggestionSelect ?: return returnValue
            mainScope.launch {
                handler(position)
            }
            return returnValue
        }

        fun onSuggestionSelect(
                returnValue: Boolean = false,
                listener: suspend MainScope.(Int) -> Boolean
        ) {
            _onSuggestionSelect = listener
            _onSuggestionSelect_returnValue = returnValue
        }

        private var _onSuggestionClick: (suspend MainScope.(Int) -> Boolean)? = null
        private var _onSuggestionClick_returnValue: Boolean = false

        override fun onSuggestionClick(position: Int) : Boolean {
            val returnValue = _onSuggestionClick_returnValue
            val handler = _onSuggestionClick ?: return returnValue
            mainScope.launch {
                handler(position)
            }
            return returnValue
        }

        fun onSuggestionClick(
                returnValue: Boolean = false,
                listener: suspend MainScope.(Int) -> Boolean
        ) {
            _onSuggestionClick = listener
            _onSuggestionClick_returnValue = returnValue
        }

    }fun androidx.appcompat.widget.Toolbar.onMenuItemClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(item: android.view.MenuItem?) -> Unit
    ) {
        setOnMenuItemClickListener { item ->
            mainScope.launch {
                handler(item)
            }
            returnValue
        }
    }

    fun androidx.appcompat.widget.ViewStubCompat.onInflate(
            handler: suspend MainScope.(stub: androidx.appcompat.widget.ViewStubCompat?, inflated: android.view.View?) -> Unit
    ) {
        setOnInflateListener { stub, inflated ->
            mainScope.launch {
                handler(stub, inflated)
            }
        }
    }

}