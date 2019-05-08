package com.bennyhuo.kotlin.coroutines.android.mainscope.job

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key

class ImmutableCoroutineContext(private val coroutineContext: CoroutineContext): CoroutineContext {
    override fun <R> fold(initial: R, operation: (R, Element) -> R): R = coroutineContext.fold(initial, operation)

    override fun <E : Element> get(key: Key<E>) = coroutineContext[key]

    override fun minusKey(key: Key<*>) = this

    override fun plus(context: CoroutineContext) = this
}