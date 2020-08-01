package com.android.github.marvelapp.marvelapp.presentation;

import android.app.Application;

import com.android.simone.github.marvelapp.BuildConfig;
import com.squareup.leakcanary.LeakCanary;


public class MarvelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
