package com.android.github.marvelapp.marvelapp.presentation.di.component;

import com.android.github.marvelapp.marvelapp.presentation.ui.list.MovieListView;
import com.android.github.marvelapp.marvelapp.presentation.di.module.DataModule;
import com.android.github.marvelapp.marvelapp.presentation.di.module.UseCaseModule;
import com.android.github.marvelapp.marvelapp.presentation.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {DataModule.class, UseCaseModule.class})
public interface MovieComponent {

    void inject(MovieListView movieListView);
}
