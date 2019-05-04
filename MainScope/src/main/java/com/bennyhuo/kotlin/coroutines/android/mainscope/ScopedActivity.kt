package com.bennyhuo.kotlin.coroutines.android.mainscope

import android.view.WindowInsets
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.launch

interface ScopedActivity: Scoped {
    fun android.view.View.onLayoutChange(
            handler: suspend MainScope.(v: android.view.View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) -> Unit
    ) {
        addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            scope.launch {
                handler(v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom)
            }
        }
    }

    fun android.view.View.onAttachStateChangeListener(
            init: __View_OnAttachStateChangeListener.() -> Unit
    ) {
        val listener = __View_OnAttachStateChangeListener(scope)
        listener.init()
        addOnAttachStateChangeListener(listener)
    }

    class __View_OnAttachStateChangeListener(private val scope: MainScope) : android.view.View.OnAttachStateChangeListener {

        private var _onViewAttachedToWindow: (suspend MainScope.(android.view.View) -> Unit)? = null


        override fun onViewAttachedToWindow(v: android.view.View) {
            val handler = _onViewAttachedToWindow ?: return
            scope.launch {
                handler(v)
            }
        }

        fun onViewAttachedToWindow(
                listener: suspend MainScope.(android.view.View) -> Unit
        ) {
            _onViewAttachedToWindow = listener
        }

        private var _onViewDetachedFromWindow: (suspend MainScope.(android.view.View) -> Unit)? = null


        override fun onViewDetachedFromWindow(v: android.view.View) {
            val handler = _onViewDetachedFromWindow ?: return
            scope.launch {
                handler(v)
            }
        }

        fun onViewDetachedFromWindow(
                listener: suspend MainScope.(android.view.View) -> Unit
        ) {
            _onViewDetachedFromWindow = listener
        }

    }fun android.view.View.onUnhandledKeyEvent(
            returnValue: Boolean = false,
            handler: suspend MainScope.(p0: android.view.View?, p1: android.view.KeyEvent?) -> Unit
    ) {
        addOnUnhandledKeyEventListener { p0, p1 ->
            scope.launch {
                handler(p0, p1)
            }
            returnValue
        }
    }

    fun android.widget.TextView.textChangedListener(
            init: __TextWatcher.() -> Unit
    ) {
        val listener = __TextWatcher(scope)
        listener.init()
        addTextChangedListener(listener)
    }

    class __TextWatcher(private val scope: MainScope) : android.text.TextWatcher {

        private var _beforeTextChanged: (suspend MainScope.(CharSequence?, Int, Int, Int) -> Unit)? = null


        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            val handler = _beforeTextChanged ?: return
            scope.launch {
                handler(s, start, count, after)
            }
        }

        fun beforeTextChanged(
                listener: suspend MainScope.(CharSequence?, Int, Int, Int) -> Unit
        ) {
            _beforeTextChanged = listener
        }

        private var _onTextChanged: (suspend MainScope.(CharSequence?, Int, Int, Int) -> Unit)? = null


        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val handler = _onTextChanged ?: return
            scope.launch {
                handler(s, start, before, count)
            }
        }

        fun onTextChanged(
                listener: suspend MainScope.(CharSequence?, Int, Int, Int) -> Unit
        ) {
            _onTextChanged = listener
        }

        private var _afterTextChanged: (suspend MainScope.(android.text.Editable?) -> Unit)? = null


        override fun afterTextChanged(s: android.text.Editable?) {
            val handler = _afterTextChanged ?: return
            scope.launch {
                handler(s)
            }
        }

        fun afterTextChanged(
                listener: suspend MainScope.(android.text.Editable?) -> Unit
        ) {
            _afterTextChanged = listener
        }

    }fun android.gesture.GestureOverlayView.onGestureListener(
            init: __GestureOverlayView_OnGestureListener.() -> Unit
    ) {
        val listener = __GestureOverlayView_OnGestureListener(scope)
        listener.init()
        addOnGestureListener(listener)
    }

    class __GestureOverlayView_OnGestureListener(private val scope: MainScope) : android.gesture.GestureOverlayView.OnGestureListener {

        private var _onGestureStarted: (suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null


        override fun onGestureStarted(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) {
            val handler = _onGestureStarted ?: return
            scope.launch {
                handler(overlay, event)
            }
        }

        fun onGestureStarted(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit
        ) {
            _onGestureStarted = listener
        }

        private var _onGesture: (suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null


        override fun onGesture(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) {
            val handler = _onGesture ?: return
            scope.launch {
                handler(overlay, event)
            }
        }

        fun onGesture(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit
        ) {
            _onGesture = listener
        }

        private var _onGestureEnded: (suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null


        override fun onGestureEnded(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) {
            val handler = _onGestureEnded ?: return
            scope.launch {
                handler(overlay, event)
            }
        }

        fun onGestureEnded(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit
        ) {
            _onGestureEnded = listener
        }

        private var _onGestureCancelled: (suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit)? = null


        override fun onGestureCancelled(overlay: android.gesture.GestureOverlayView?, event: android.view.MotionEvent?) {
            val handler = _onGestureCancelled ?: return
            scope.launch {
                handler(overlay, event)
            }
        }

        fun onGestureCancelled(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?, android.view.MotionEvent?) -> Unit
        ) {
            _onGestureCancelled = listener
        }

    }fun android.gesture.GestureOverlayView.onGesturePerformed(
            handler: suspend MainScope.(overlay: android.gesture.GestureOverlayView?, gesture: android.gesture.Gesture?) -> Unit
    ) {
        addOnGesturePerformedListener { overlay, gesture ->
            scope.launch {
                handler(overlay, gesture)
            }
        }
    }

    fun android.gesture.GestureOverlayView.onGesturingListener(
            init: __GestureOverlayView_OnGesturingListener.() -> Unit
    ) {
        val listener = __GestureOverlayView_OnGesturingListener(scope)
        listener.init()
        addOnGesturingListener(listener)
    }

    class __GestureOverlayView_OnGesturingListener(private val scope: MainScope) : android.gesture.GestureOverlayView.OnGesturingListener {

        private var _onGesturingStarted: (suspend MainScope.(android.gesture.GestureOverlayView?) -> Unit)? = null


        override fun onGesturingStarted(overlay: android.gesture.GestureOverlayView?) {
            val handler = _onGesturingStarted ?: return
            scope.launch {
                handler(overlay)
            }
        }

        fun onGesturingStarted(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?) -> Unit
        ) {
            _onGesturingStarted = listener
        }

        private var _onGesturingEnded: (suspend MainScope.(android.gesture.GestureOverlayView?) -> Unit)? = null


        override fun onGesturingEnded(overlay: android.gesture.GestureOverlayView?) {
            val handler = _onGesturingEnded ?: return
            scope.launch {
                handler(overlay)
            }
        }

        fun onGesturingEnded(
                listener: suspend MainScope.(android.gesture.GestureOverlayView?) -> Unit
        ) {
            _onGesturingEnded = listener
        }

    }fun android.media.tv.TvView.onUnhandledInputEvent(
            returnValue: Boolean = false,
            handler: suspend MainScope.(event: android.view.InputEvent?) -> Unit
    ) {
        setOnUnhandledInputEventListener { event ->
            scope.launch {
                handler(event)
            }
            returnValue
        }
    }

    fun android.view.View.onApplyWindowInsets(
            returnValue: WindowInsets,
            handler: suspend MainScope.(v: android.view.View?, insets: android.view.WindowInsets?) -> Unit
    ) {
        setOnApplyWindowInsetsListener { v, insets ->
            scope.launch {
                handler(v, insets)
            }
            returnValue
        }
    }

    fun android.view.View.onCapturedPointer(
            returnValue: Boolean = false,
            handler: suspend MainScope.(view: android.view.View?, event: android.view.MotionEvent?) -> Unit
    ) {
        setOnCapturedPointerListener { view, event ->
            scope.launch {
                handler(view, event)
            }
            returnValue
        }
    }

    fun android.view.View.onClick(
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnClickListener { v ->
            scope.launch {
                handler(v)
            }
        }
    }

    fun android.view.View.onContextClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnContextClickListener { v ->
            scope.launch {
                handler(v)
            }
            returnValue
        }
    }

    fun android.view.View.onCreateContextMenu(
            handler: suspend MainScope.(menu: android.view.ContextMenu?, v: android.view.View?, menuInfo: android.view.ContextMenu.ContextMenuInfo?) -> Unit
    ) {
        setOnCreateContextMenuListener { menu, v, menuInfo ->
            scope.launch {
                handler(menu, v, menuInfo)
            }
        }
    }

    fun android.view.View.onDrag(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View, event: android.view.DragEvent) -> Unit
    ) {
        setOnDragListener { v, event ->
            scope.launch {
                handler(v, event)
            }
            returnValue
        }
    }

    fun android.view.View.onFocusChange(
            handler: suspend MainScope.(v: android.view.View, hasFocus: Boolean) -> Unit
    ) {
        setOnFocusChangeListener { v, hasFocus ->
            scope.launch {
                handler(v, hasFocus)
            }
        }
    }

    fun android.view.View.onGenericMotion(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View, event: android.view.MotionEvent) -> Unit
    ) {
        setOnGenericMotionListener { v, event ->
            scope.launch {
                handler(v, event)
            }
            returnValue
        }
    }

    fun android.view.View.onHover(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View, event: android.view.MotionEvent) -> Unit
    ) {
        setOnHoverListener { v, event ->
            scope.launch {
                handler(v, event)
            }
            returnValue
        }
    }

    fun android.view.View.onKey(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View, keyCode: Int, event: android.view.KeyEvent?) -> Unit
    ) {
        setOnKeyListener { v, keyCode, event ->
            scope.launch {
                handler(v, keyCode, event)
            }
            returnValue
        }
    }

    fun android.view.View.onLongClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnLongClickListener { v ->
            scope.launch {
                handler(v)
            }
            returnValue
        }
    }

    fun android.view.View.onScrollChange(
            handler: suspend MainScope.(v: android.view.View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) -> Unit
    ) {
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            scope.launch {
                handler(v, scrollX, scrollY, oldScrollX, oldScrollY)
            }
        }
    }

    fun android.view.View.onSystemUiVisibilityChange(
            handler: suspend MainScope.(visibility: Int) -> Unit
    ) {
        setOnSystemUiVisibilityChangeListener { visibility ->
            scope.launch {
                handler(visibility)
            }
        }
    }

    fun android.view.View.onTouch(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.view.View, event: android.view.MotionEvent) -> Unit
    ) {
        setOnTouchListener { v, event ->
            scope.launch {
                handler(v, event)
            }
            returnValue
        }
    }

    fun android.view.ViewGroup.onHierarchyChangeListener(
            init: __ViewGroup_OnHierarchyChangeListener.() -> Unit
    ) {
        val listener = __ViewGroup_OnHierarchyChangeListener(scope)
        listener.init()
        setOnHierarchyChangeListener(listener)
    }

    class __ViewGroup_OnHierarchyChangeListener(private val scope: MainScope) : android.view.ViewGroup.OnHierarchyChangeListener {

        private var _onChildViewAdded: (suspend MainScope.(android.view.View?, android.view.View?) -> Unit)? = null


        override fun onChildViewAdded(parent: android.view.View?, child: android.view.View?) {
            val handler = _onChildViewAdded ?: return
            scope.launch {
                handler(parent, child)
            }
        }

        fun onChildViewAdded(
                listener: suspend MainScope.(android.view.View?, android.view.View?) -> Unit
        ) {
            _onChildViewAdded = listener
        }

        private var _onChildViewRemoved: (suspend MainScope.(android.view.View?, android.view.View?) -> Unit)? = null


        override fun onChildViewRemoved(parent: android.view.View?, child: android.view.View?) {
            val handler = _onChildViewRemoved ?: return
            scope.launch {
                handler(parent, child)
            }
        }

        fun onChildViewRemoved(
                listener: suspend MainScope.(android.view.View?, android.view.View?) -> Unit
        ) {
            _onChildViewRemoved = listener
        }

    }fun android.view.ViewStub.onInflate(
            handler: suspend MainScope.(stub: android.view.ViewStub?, inflated: android.view.View?) -> Unit
    ) {
        setOnInflateListener { stub, inflated ->
            scope.launch {
                handler(stub, inflated)
            }
        }
    }

    fun android.widget.AbsListView.onScrollListener(
            init: __AbsListView_OnScrollListener.() -> Unit
    ) {
        val listener = __AbsListView_OnScrollListener(scope)
        listener.init()
        setOnScrollListener(listener)
    }

    class __AbsListView_OnScrollListener(private val scope: MainScope) : android.widget.AbsListView.OnScrollListener {

        private var _onScrollStateChanged: (suspend MainScope.(android.widget.AbsListView?, Int) -> Unit)? = null


        override fun onScrollStateChanged(view: android.widget.AbsListView?, scrollState: Int) {
            val handler = _onScrollStateChanged ?: return
            scope.launch {
                handler(view, scrollState)
            }
        }

        fun onScrollStateChanged(
                listener: suspend MainScope.(android.widget.AbsListView?, Int) -> Unit
        ) {
            _onScrollStateChanged = listener
        }

        private var _onScroll: (suspend MainScope.(android.widget.AbsListView?, Int, Int, Int) -> Unit)? = null


        override fun onScroll(view: android.widget.AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
            val handler = _onScroll ?: return
            scope.launch {
                handler(view, firstVisibleItem, visibleItemCount, totalItemCount)
            }
        }

        fun onScroll(
                listener: suspend MainScope.(android.widget.AbsListView?, Int, Int, Int) -> Unit
        ) {
            _onScroll = listener
        }

    }fun android.widget.ActionMenuView.onMenuItemClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(item: android.view.MenuItem?) -> Unit
    ) {
        setOnMenuItemClickListener { item ->
            scope.launch {
                handler(item)
            }
            returnValue
        }
    }

    fun android.widget.AdapterView<out android.widget.Adapter>.onItemClick(
            handler: suspend MainScope.(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit
    ) {
        setOnItemClickListener { p0, p1, p2, p3 ->
            scope.launch {
                handler(p0, p1, p2, p3)
            }
        }
    }

    fun android.widget.AdapterView<out android.widget.Adapter>.onItemLongClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) -> Unit
    ) {
        setOnItemLongClickListener { p0, p1, p2, p3 ->
            scope.launch {
                handler(p0, p1, p2, p3)
            }
            returnValue
        }
    }

    fun android.widget.AdapterView<out android.widget.Adapter>.onItemSelectedListener(
            init: __AdapterView_OnItemSelectedListener.() -> Unit
    ) {
        val listener = __AdapterView_OnItemSelectedListener(scope)
        listener.init()
        setOnItemSelectedListener(listener)
    }

    class __AdapterView_OnItemSelectedListener(private val scope: MainScope) : android.widget.AdapterView.OnItemSelectedListener {

        private var _onItemSelected: (suspend MainScope.(android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit)? = null


        override fun onItemSelected(p0: android.widget.AdapterView<*>?, p1: android.view.View?, p2: Int, p3: Long) {
            val handler = _onItemSelected ?: return
            scope.launch {
                handler(p0, p1, p2, p3)
            }
        }

        fun onItemSelected(
                listener: suspend MainScope.(android.widget.AdapterView<*>?, android.view.View?, Int, Long) -> Unit
        ) {
            _onItemSelected = listener
        }

        private var _onNothingSelected: (suspend MainScope.(android.widget.AdapterView<*>?) -> Unit)? = null


        override fun onNothingSelected(p0: android.widget.AdapterView<*>?) {
            val handler = _onNothingSelected ?: return
            scope.launch {
                handler(p0)
            }
        }

        fun onNothingSelected(
                listener: suspend MainScope.(android.widget.AdapterView<*>?) -> Unit
        ) {
            _onNothingSelected = listener
        }

    }fun android.widget.AutoCompleteTextView.onDismiss(
            handler: suspend MainScope.() -> Unit
    ) {
        setOnDismissListener {  ->
            scope.launch(block = handler)
        }
    }

    fun android.widget.CalendarView.onDateChange(
            handler: suspend MainScope.(view: android.widget.CalendarView?, year: Int, month: Int, dayOfMonth: Int) -> Unit
    ) {
        setOnDateChangeListener { view, year, month, dayOfMonth ->
            scope.launch {
                handler(view, year, month, dayOfMonth)
            }
        }
    }

    fun android.widget.Chronometer.onChronometerTick(
            handler: suspend MainScope.(chronometer: android.widget.Chronometer?) -> Unit
    ) {
        setOnChronometerTickListener { chronometer ->
            scope.launch {
                handler(chronometer)
            }
        }
    }

    fun android.widget.CompoundButton.onCheckedChange(
            handler: suspend MainScope.(buttonView: android.widget.CompoundButton?, isChecked: Boolean) -> Unit
    ) {
        setOnCheckedChangeListener { buttonView, isChecked ->
            scope.launch {
                handler(buttonView, isChecked)
            }
        }
    }

    fun android.widget.DatePicker.onDateChanged(
            handler: suspend MainScope.(view: android.widget.DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) -> Unit
    ) {
        setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            scope.launch {
                handler(view, year, monthOfYear, dayOfMonth)
            }
        }
    }

    fun android.widget.ExpandableListView.onChildClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(parent: android.widget.ExpandableListView?, v: android.view.View?, groupPosition: Int, childPosition: Int, id: Long) -> Unit
    ) {
        setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            scope.launch {
                handler(parent, v, groupPosition, childPosition, id)
            }
            returnValue
        }
    }

    fun android.widget.ExpandableListView.onGroupClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(parent: android.widget.ExpandableListView?, v: android.view.View?, groupPosition: Int, id: Long) -> Unit
    ) {
        setOnGroupClickListener { parent, v, groupPosition, id ->
            scope.launch {
                handler(parent, v, groupPosition, id)
            }
            returnValue
        }
    }

    fun android.widget.ExpandableListView.onGroupCollapse(
            handler: suspend MainScope.(groupPosition: Int) -> Unit
    ) {
        setOnGroupCollapseListener { groupPosition ->
            scope.launch {
                handler(groupPosition)
            }
        }
    }

    fun android.widget.ExpandableListView.onGroupExpand(
            handler: suspend MainScope.(groupPosition: Int) -> Unit
    ) {
        setOnGroupExpandListener { groupPosition ->
            scope.launch {
                handler(groupPosition)
            }
        }
    }

    fun android.widget.NumberPicker.onScroll(
            handler: suspend MainScope.(view: android.widget.NumberPicker?, scrollState: Int) -> Unit
    ) {
        setOnScrollListener { view, scrollState ->
            scope.launch {
                handler(view, scrollState)
            }
        }
    }

    fun android.widget.NumberPicker.onValueChanged(
            handler: suspend MainScope.(picker: android.widget.NumberPicker?, oldVal: Int, newVal: Int) -> Unit
    ) {
        setOnValueChangedListener { picker, oldVal, newVal ->
            scope.launch {
                handler(picker, oldVal, newVal)
            }
        }
    }

    fun android.widget.RadioGroup.onCheckedChange(
            handler: suspend MainScope.(group: android.widget.RadioGroup?, checkedId: Int) -> Unit
    ) {
        setOnCheckedChangeListener { group, checkedId ->
            scope.launch {
                handler(group, checkedId)
            }
        }
    }

    fun android.widget.RatingBar.onRatingBarChange(
            handler: suspend MainScope.(ratingBar: android.widget.RatingBar?, rating: Float, fromUser: Boolean) -> Unit
    ) {
        setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            scope.launch {
                handler(ratingBar, rating, fromUser)
            }
        }
    }

    fun android.widget.SearchView.onClose(
            returnValue: Boolean = false,
            handler: suspend MainScope.() -> Unit
    ) {
        setOnCloseListener {  ->
            scope.launch(block = handler)
            returnValue
        }
    }

    fun android.widget.SearchView.onQueryTextFocusChange(
            handler: suspend MainScope.(v: android.view.View, hasFocus: Boolean) -> Unit
    ) {
        setOnQueryTextFocusChangeListener { v, hasFocus ->
            scope.launch {
                handler(v, hasFocus)
            }
        }
    }

    fun android.widget.SearchView.onQueryTextListener(
            init: __SearchView_OnQueryTextListener.() -> Unit
    ) {
        val listener = __SearchView_OnQueryTextListener(scope)
        listener.init()
        setOnQueryTextListener(listener)
    }

    class __SearchView_OnQueryTextListener(private val scope: MainScope) : android.widget.SearchView.OnQueryTextListener {

        private var _onQueryTextSubmit: (suspend MainScope.(String?) -> Boolean)? = null
        private var _onQueryTextSubmit_returnValue: Boolean = false

        override fun onQueryTextSubmit(query: String?) : Boolean {
            val returnValue = _onQueryTextSubmit_returnValue
            val handler = _onQueryTextSubmit ?: return returnValue
            scope.launch {
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
            scope.launch {
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

    }fun android.widget.SearchView.onSearchClick(
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnSearchClickListener { v ->
            scope.launch {
                handler(v)
            }
        }
    }

    fun android.widget.SearchView.onSuggestionListener(
            init: __SearchView_OnSuggestionListener.() -> Unit
    ) {
        val listener = __SearchView_OnSuggestionListener(scope)
        listener.init()
        setOnSuggestionListener(listener)
    }

    class __SearchView_OnSuggestionListener(private val scope: MainScope) : android.widget.SearchView.OnSuggestionListener {

        private var _onSuggestionSelect: (suspend MainScope.(Int) -> Boolean)? = null
        private var _onSuggestionSelect_returnValue: Boolean = false

        override fun onSuggestionSelect(position: Int) : Boolean {
            val returnValue = _onSuggestionSelect_returnValue
            val handler = _onSuggestionSelect ?: return returnValue
            scope.launch {
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
            scope.launch {
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

    }fun android.widget.SeekBar.onSeekBarChangeListener(
            init: __SeekBar_OnSeekBarChangeListener.() -> Unit
    ) {
        val listener = __SeekBar_OnSeekBarChangeListener(scope)
        listener.init()
        setOnSeekBarChangeListener(listener)
    }

    class __SeekBar_OnSeekBarChangeListener(private val scope: MainScope) : android.widget.SeekBar.OnSeekBarChangeListener {

        private var _onProgressChanged: (suspend MainScope.(android.widget.SeekBar?, Int, Boolean) -> Unit)? = null


        override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
            val handler = _onProgressChanged ?: return
            scope.launch {
                handler(seekBar, progress, fromUser)
            }
        }

        fun onProgressChanged(
                listener: suspend MainScope.(android.widget.SeekBar?, Int, Boolean) -> Unit
        ) {
            _onProgressChanged = listener
        }

        private var _onStartTrackingTouch: (suspend MainScope.(android.widget.SeekBar?) -> Unit)? = null


        override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {
            val handler = _onStartTrackingTouch ?: return
            scope.launch {
                handler(seekBar)
            }
        }

        fun onStartTrackingTouch(
                listener: suspend MainScope.(android.widget.SeekBar?) -> Unit
        ) {
            _onStartTrackingTouch = listener
        }

        private var _onStopTrackingTouch: (suspend MainScope.(android.widget.SeekBar?) -> Unit)? = null


        override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {
            val handler = _onStopTrackingTouch ?: return
            scope.launch {
                handler(seekBar)
            }
        }

        fun onStopTrackingTouch(
                listener: suspend MainScope.(android.widget.SeekBar?) -> Unit
        ) {
            _onStopTrackingTouch = listener
        }

    }fun android.widget.SlidingDrawer.onDrawerClose(
            handler: suspend MainScope.() -> Unit
    ) {
        setOnDrawerCloseListener {  ->
            scope.launch(block = handler)
        }
    }

    fun android.widget.SlidingDrawer.onDrawerOpen(
            handler: suspend MainScope.() -> Unit
    ) {
        setOnDrawerOpenListener {  ->
            scope.launch(block = handler)
        }
    }

    fun android.widget.SlidingDrawer.onDrawerScrollListener(
            init: __SlidingDrawer_OnDrawerScrollListener.() -> Unit
    ) {
        val listener = __SlidingDrawer_OnDrawerScrollListener(scope)
        listener.init()
        setOnDrawerScrollListener(listener)
    }

    class __SlidingDrawer_OnDrawerScrollListener(private val scope: MainScope) : android.widget.SlidingDrawer.OnDrawerScrollListener {

        private var _onScrollStarted: (suspend MainScope.() -> Unit)? = null


        override fun onScrollStarted() {
            val handler = _onScrollStarted ?: return
            scope.launch(block = handler)
        }

        fun onScrollStarted(
                listener: suspend MainScope.() -> Unit
        ) {
            _onScrollStarted = listener
        }

        private var _onScrollEnded: (suspend MainScope.() -> Unit)? = null


        override fun onScrollEnded() {
            val handler = _onScrollEnded ?: return
            scope.launch(block = handler)
        }

        fun onScrollEnded(
                listener: suspend MainScope.() -> Unit
        ) {
            _onScrollEnded = listener
        }

    }fun android.widget.TabHost.onTabChanged(
            handler: suspend MainScope.(tabId: String?) -> Unit
    ) {
        setOnTabChangedListener { tabId ->
            scope.launch {
                handler(tabId)
            }
        }
    }

    fun android.widget.TextView.onEditorAction(
            returnValue: Boolean = false,
            handler: suspend MainScope.(v: android.widget.TextView?, actionId: Int, event: android.view.KeyEvent?) -> Unit
    ) {
        setOnEditorActionListener { v, actionId, event ->
            scope.launch {
                handler(v, actionId, event)
            }
            returnValue
        }
    }

    fun android.widget.TimePicker.onTimeChanged(
            handler: suspend MainScope.(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) -> Unit
    ) {
        setOnTimeChangedListener { view, hourOfDay, minute ->
            scope.launch {
                handler(view, hourOfDay, minute)
            }
        }
    }

    fun android.widget.Toolbar.onMenuItemClick(
            returnValue: Boolean = false,
            handler: suspend MainScope.(item: android.view.MenuItem?) -> Unit
    ) {
        setOnMenuItemClickListener { item ->
            scope.launch {
                handler(item)
            }
            returnValue
        }
    }

    fun android.widget.VideoView.onCompletion(
            handler: suspend MainScope.(mp: android.media.MediaPlayer?) -> Unit
    ) {
        setOnCompletionListener { mp ->
            scope.launch {
                handler(mp)
            }
        }
    }

    fun android.widget.VideoView.onError(
            returnValue: Boolean = false,
            handler: suspend MainScope.(mp: android.media.MediaPlayer?, what: Int, extra: Int) -> Unit
    ) {
        setOnErrorListener { mp, what, extra ->
            scope.launch {
                handler(mp, what, extra)
            }
            returnValue
        }
    }

    fun android.widget.VideoView.onInfo(
            returnValue: Boolean = false,
            handler: suspend MainScope.(mp: android.media.MediaPlayer?, what: Int, extra: Int) -> Unit
    ) {
        setOnInfoListener { mp, what, extra ->
            scope.launch {
                handler(mp, what, extra)
            }
            returnValue
        }
    }

    fun android.widget.VideoView.onPrepared(
            handler: suspend MainScope.(mp: android.media.MediaPlayer?) -> Unit
    ) {
        setOnPreparedListener { mp ->
            scope.launch {
                handler(mp)
            }
        }
    }

    fun android.widget.ZoomControls.onZoomInClick(
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnZoomInClickListener { v ->
            scope.launch {
                handler(v)
            }
        }
    }

    fun android.widget.ZoomControls.onZoomOutClick(
            handler: suspend MainScope.(v: android.view.View?) -> Unit
    ) {
        setOnZoomOutClickListener { v ->
            scope.launch {
                handler(v)
            }
        }
    }

}
