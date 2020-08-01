package com.android.github.marvelapp.marvelapp.presentation.di.component;

import com.android.github.marvelapp.marvelapp.presentation.ui.BaseActivity;
import com.android.github.marvelapp.marvelapp.domain.executor.PostExecutionThread;
import com.android.github.marvelapp.marvelapp.domain.executor.ThreadExecution;
import com.android.github.marvelapp.marvelapp.domain.repository.MoviewRepository;
import com.android.github.marvelapp.marvelapp.presentation.di.module.ApplicationModule;
import com.android.github.marvelapp.marvelapp.presentation.di.module.ContextModule;
import com.android.github.marvelapp.marvelapp.presentation.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, ContextModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    ThreadExecution threadExecution();

    PostExecutionThread postExecutionThread();

    MoviewRepository comicRepository();
}
