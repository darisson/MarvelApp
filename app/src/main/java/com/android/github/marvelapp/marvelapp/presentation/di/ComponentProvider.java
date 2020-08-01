package com.android.github.marvelapp.marvelapp.presentation.di;

import android.app.Activity;

import com.android.github.marvelapp.marvelapp.presentation.di.component.ApplicationComponent;
import com.android.github.marvelapp.marvelapp.presentation.di.component.DaggerApplicationComponent;
import com.android.github.marvelapp.marvelapp.presentation.di.component.DaggerMovieComponent;
import com.android.github.marvelapp.marvelapp.presentation.di.component.MovieComponent;
import com.android.github.marvelapp.marvelapp.presentation.di.module.ContextModule;

public final class ComponentProvider {

    private static ApplicationComponent applicationComponent;
    private static MovieComponent movieComponent;

    public static ApplicationComponent provideApplicationComponent(Activity activity) {
//        if (applicationComponent == null) {
//            applicationComponent = DaggerApplicationComponent.builder()
//                    .contextModule(new ContextModule(activity))
//                    .build();
//        }
//        return applicationComponent;
        return DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(activity))
                .build();
    }


    public static MovieComponent provideComicComponent(ApplicationComponent applicationComponent) {
//        if (comicComponent == null) {
//            comicComponent = DaggerComicComponent.builder()
//                    .applicationComponent(applicationComponent)
//                    .build();
//        }
//        return comicComponent;
        return DaggerMovieComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }
}
