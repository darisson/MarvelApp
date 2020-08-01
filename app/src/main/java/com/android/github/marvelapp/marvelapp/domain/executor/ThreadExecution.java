package com.android.github.marvelapp.marvelapp.domain.executor;

import rx.Scheduler;

public interface ThreadExecution {
    Scheduler getScheduler();
}
