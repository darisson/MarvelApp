package com.android.github.marvelapp.marvelapp.domain.executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.schedulers.Schedulers;

@Singleton
public class JobExecutor implements ThreadExecution {

    @Inject
    public JobExecutor() {
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
