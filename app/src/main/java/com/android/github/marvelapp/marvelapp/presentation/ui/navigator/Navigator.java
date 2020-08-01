package com.android.github.marvelapp.marvelapp.presentation.ui.navigator;

import android.app.Activity;
import android.content.Intent;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.ui.detail.MovieDetailActivity;
import com.android.github.marvelapp.marvelapp.presentation.ui.list.MovieListActivity;

import javax.inject.Inject;

public class Navigator {

    private Activity activity;

    @Inject
    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void close() {
        activity.finish();
    }

    public void showMovieList() {
        Intent intent = MovieListActivity.newIntent(activity);
        activity.startActivity(intent);
    }

    public void showMovieDetail(MovieResponse movieModel) {
        Intent intent = MovieDetailActivity.newIntent(activity, movieModel);
        activity.startActivity(intent);
    }

}
