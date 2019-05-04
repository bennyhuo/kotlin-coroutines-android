package com.bennyhuo.kotlin.coroutines.android.mainscope.job;

import com.bennyhuo.kotlin.coroutines.android.mainscope.MainScope;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

class StandaloneCoroutineCompat extends AbstractCoroutine<Unit> implements MainScope {
    public StandaloneCoroutineCompat(@NotNull CoroutineContext parentContext, boolean active) {
        super(parentContext, active);
    }

    @Override
    protected boolean handleJobException(@NotNull Throwable exception) {
        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), exception);
        return true;
    }
}
