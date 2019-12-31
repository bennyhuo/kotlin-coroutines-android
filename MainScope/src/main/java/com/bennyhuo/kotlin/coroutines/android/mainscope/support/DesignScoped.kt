package com.bennyhuo.kotlin.coroutines.android.mainscope.support

import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout.Tab
import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope
import com.bennyhuo.kotlin.coroutines.android.mainscope.job.launch
import com.bennyhuo.kotlin.coroutines.android.mainscope.scope.BasicScoped

interface DesignScoped: BasicScoped {
    fun android.support.design.widget.AppBarLayout.onOffsetChanged(
            handler: suspend MainScope.(appBarLayout: android.support.design.widget.AppBarLayout?, verticalOffset: Int) -> Unit
    ) {
        addOnOffsetChangedListener (AppBarLayout.OnOffsetChangedListener{ appBarLayout, verticalOffset ->
            mainScope.launch {
                handler(appBarLayout, verticalOffset)
            }
        })
    }

    fun <T: Tab> android.support.design.widget.TabLayout.onTabSelectedListener(
            init: __onTabSelected_TabLayout_BaseOnTabSelectedListener<T>.() -> Unit
    ) {
        val listener = __onTabSelected_TabLayout_BaseOnTabSelectedListener<T>(mainScope)
        listener.init()
        addOnTabSelectedListener(listener)
    }

    class __onTabSelected_TabLayout_BaseOnTabSelectedListener<T: Tab>(private val mainScope: MainScope) : android.support.design.widget.TabLayout.BaseOnTabSelectedListener<T> {

        private var _onTabSelectedWithP0: (suspend MainScope.(T?) -> Unit)? = null


        override fun onTabSelected(p0: T?) {
            val handler = _onTabSelectedWithP0 ?: return
            mainScope.launch {
                handler(p0)
            }
        }

        fun onTabSelected(
                listener: suspend MainScope.(T?) -> Unit
        ) {
            _onTabSelectedWithP0 = listener
        }

        private var _onTabUnselectedWithP0: (suspend MainScope.(T?) -> Unit)? = null


        override fun onTabUnselected(p0: T?) {
            val handler = _onTabUnselectedWithP0 ?: return
            mainScope.launch {
                handler(p0)
            }
        }

        fun onTabUnselected(
                listener: suspend MainScope.(T?) -> Unit
        ) {
            _onTabUnselectedWithP0 = listener
        }

        private var _onTabReselectedWithP0: (suspend MainScope.(T?) -> Unit)? = null


        override fun onTabReselected(p0: T?) {
            val handler = _onTabReselectedWithP0 ?: return
            mainScope.launch {
                handler(p0)
            }
        }

        fun onTabReselected(
                listener: suspend MainScope.(T?) -> Unit
        ) {
            _onTabReselectedWithP0 = listener
        }

    }fun android.support.design.widget.FloatingActionButton.onShowAnimationListener(
            init: __onShowAnimation_Animator_AnimatorListener.() -> Unit
    ) {
        val listener = __onShowAnimation_Animator_AnimatorListener(mainScope)
        listener.init()
        addOnShowAnimationListener(listener)
    }

    class __onShowAnimation_Animator_AnimatorListener(private val mainScope: MainScope) : android.animation.Animator.AnimatorListener {

        private var _onAnimationStartWithAnimationAndIsReverse: (suspend MainScope.(android.animation.Animator?, Boolean) -> Unit)? = null


        override fun onAnimationStart(animation: android.animation.Animator?, isReverse: Boolean) {
            val handler = _onAnimationStartWithAnimationAndIsReverse ?: return
            mainScope.launch {
                handler(animation, isReverse)
            }
        }

        fun onAnimationStart(
                listener: suspend MainScope.(android.animation.Animator?, Boolean) -> Unit
        ) {
            _onAnimationStartWithAnimationAndIsReverse = listener
        }

        private var _onAnimationEndWithAnimationAndIsReverse: (suspend MainScope.(android.animation.Animator?, Boolean) -> Unit)? = null


        override fun onAnimationEnd(animation: android.animation.Animator?, isReverse: Boolean) {
            val handler = _onAnimationEndWithAnimationAndIsReverse ?: return
            mainScope.launch {
                handler(animation, isReverse)
            }
        }

        fun onAnimationEnd(
                listener: suspend MainScope.(android.animation.Animator?, Boolean) -> Unit
        ) {
            _onAnimationEndWithAnimationAndIsReverse = listener
        }

        private var _onAnimationStartWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationStart(animation: android.animation.Animator?) {
            val handler = _onAnimationStartWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationStart(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationStartWithAnimation = listener
        }

        private var _onAnimationEndWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationEnd(animation: android.animation.Animator?) {
            val handler = _onAnimationEndWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationEnd(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationEndWithAnimation = listener
        }

        private var _onAnimationCancelWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationCancel(animation: android.animation.Animator?) {
            val handler = _onAnimationCancelWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationCancel(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationCancelWithAnimation = listener
        }

        private var _onAnimationRepeatWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationRepeat(animation: android.animation.Animator?) {
            val handler = _onAnimationRepeatWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationRepeat(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationRepeatWithAnimation = listener
        }

    }fun android.support.design.widget.FloatingActionButton.onHideAnimationListener(
            init: __onHideAnimation_Animator_AnimatorListener.() -> Unit
    ) {
        val listener = __onHideAnimation_Animator_AnimatorListener(mainScope)
        listener.init()
        addOnHideAnimationListener(listener)
    }

    class __onHideAnimation_Animator_AnimatorListener(private val mainScope: MainScope) : android.animation.Animator.AnimatorListener {

        private var _onAnimationStartWithAnimationAndIsReverse: (suspend MainScope.(android.animation.Animator?, Boolean) -> Unit)? = null


        override fun onAnimationStart(animation: android.animation.Animator?, isReverse: Boolean) {
            val handler = _onAnimationStartWithAnimationAndIsReverse ?: return
            mainScope.launch {
                handler(animation, isReverse)
            }
        }

        fun onAnimationStart(
                listener: suspend MainScope.(android.animation.Animator?, Boolean) -> Unit
        ) {
            _onAnimationStartWithAnimationAndIsReverse = listener
        }

        private var _onAnimationEndWithAnimationAndIsReverse: (suspend MainScope.(android.animation.Animator?, Boolean) -> Unit)? = null


        override fun onAnimationEnd(animation: android.animation.Animator?, isReverse: Boolean) {
            val handler = _onAnimationEndWithAnimationAndIsReverse ?: return
            mainScope.launch {
                handler(animation, isReverse)
            }
        }

        fun onAnimationEnd(
                listener: suspend MainScope.(android.animation.Animator?, Boolean) -> Unit
        ) {
            _onAnimationEndWithAnimationAndIsReverse = listener
        }

        private var _onAnimationStartWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationStart(animation: android.animation.Animator?) {
            val handler = _onAnimationStartWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationStart(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationStartWithAnimation = listener
        }

        private var _onAnimationEndWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationEnd(animation: android.animation.Animator?) {
            val handler = _onAnimationEndWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationEnd(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationEndWithAnimation = listener
        }

        private var _onAnimationCancelWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationCancel(animation: android.animation.Animator?) {
            val handler = _onAnimationCancelWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationCancel(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationCancelWithAnimation = listener
        }

        private var _onAnimationRepeatWithAnimation: (suspend MainScope.(android.animation.Animator?) -> Unit)? = null


        override fun onAnimationRepeat(animation: android.animation.Animator?) {
            val handler = _onAnimationRepeatWithAnimation ?: return
            mainScope.launch {
                handler(animation)
            }
        }

        fun onAnimationRepeat(
                listener: suspend MainScope.(android.animation.Animator?) -> Unit
        ) {
            _onAnimationRepeatWithAnimation = listener
        }

    }fun android.support.design.chip.ChipGroup.onCheckedChange(
            handler: suspend MainScope.(p0: android.support.design.chip.ChipGroup?, p1: Int) -> Unit
    ) {
        setOnCheckedChangeListener { p0, p1 ->
            mainScope.launch {
                handler(p0, p1)
            }
        }
    }

    fun android.support.design.chip.ChipGroup.onHierarchyChangeListener(
            init: __onHierarchyChange_ViewGroup_OnHierarchyChangeListener.() -> Unit
    ) {
        val listener = __onHierarchyChange_ViewGroup_OnHierarchyChangeListener(mainScope)
        listener.init()
        setOnHierarchyChangeListener(listener)
    }

    class __onHierarchyChange_ViewGroup_OnHierarchyChangeListener(private val mainScope: MainScope) : android.view.ViewGroup.OnHierarchyChangeListener {

        private var _onChildViewAddedWithParentAndChild: (suspend MainScope.(android.view.View?, android.view.View?) -> Unit)? = null


        override fun onChildViewAdded(parent: android.view.View?, child: android.view.View?) {
            val handler = _onChildViewAddedWithParentAndChild ?: return
            mainScope.launch {
                handler(parent, child)
            }
        }

        fun onChildViewAdded(
                listener: suspend MainScope.(android.view.View?, android.view.View?) -> Unit
        ) {
            _onChildViewAddedWithParentAndChild = listener
        }

        private var _onChildViewRemovedWithParentAndChild: (suspend MainScope.(android.view.View?, android.view.View?) -> Unit)? = null


        override fun onChildViewRemoved(parent: android.view.View?, child: android.view.View?) {
            val handler = _onChildViewRemovedWithParentAndChild ?: return
            mainScope.launch {
                handler(parent, child)
            }
        }

        fun onChildViewRemoved(
                listener: suspend MainScope.(android.view.View?, android.view.View?) -> Unit
        ) {
            _onChildViewRemovedWithParentAndChild = listener
        }

    }fun android.support.design.widget.BottomNavigationView.onNavigationItemReselected(
            handler: suspend MainScope.(item: android.view.MenuItem) -> Unit
    ) {
        setOnNavigationItemReselectedListener { item ->
            mainScope.launch {
                handler(item)
            }
        }
    }

    fun android.support.design.widget.BottomNavigationView.onNavigationItemSelected(
            returnValue: Boolean = false,
            handler: suspend MainScope.(item: android.view.MenuItem) -> Unit
    ) {
        setOnNavigationItemSelectedListener { item ->
            mainScope.launch {
                handler(item)
            }
            returnValue
        }
    }
}