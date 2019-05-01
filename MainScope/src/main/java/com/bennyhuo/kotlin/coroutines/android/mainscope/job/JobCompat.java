package com.bennyhuo.kotlin.coroutines.android.mainscope.job;

import org.jetbrains.annotations.NotNull;

import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.Job;

abstract class JobCompat implements Job {
     @NotNull
    @Override
    public ChildHandle attachChild(@NotNull ChildJob childJob) {
        throw new UnsupportedOperationException();
    }
}
