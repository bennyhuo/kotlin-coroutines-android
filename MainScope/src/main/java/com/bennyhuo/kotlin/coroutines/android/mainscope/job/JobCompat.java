package com.bennyhuo.kotlin.coroutines.android.mainscope.job;

import org.jetbrains.annotations.NotNull;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.Job;

abstract class JobCompat implements Job {
     @NotNull
    @Override
     public final ChildHandle attachChild(@NotNull ChildJob childJob) {
         //Parent is already cancelled. So cancel child directly.
         childJob.cancel(getCancellationException());
         return EmptyChildHandle.instance;
    }

    private static class EmptyChildHandle implements ChildHandle {
        private static final EmptyChildHandle instance = new EmptyChildHandle();

        @Override
        public boolean childCancelled(@NotNull Throwable throwable) {
            return true;
        }

        @Override
        public void dispose() { }
    }

    //solutions for AbstractMethodError.
    @NotNull
    @Override
    public final CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }
}
