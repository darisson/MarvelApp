package com.android.github.marvelapp.marvelapp.presentation.ui.navigator;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;

import javax.inject.Inject;

public class MovieFlowCoordinator {

    private final Navigator navigator;

    @Inject
    public MovieFlowCoordinator(Navigator navigator) {
        this.navigator = navigator;
    }


    public void start() {
        /* no-op */
    }

    public void readComicDetail(MovieResponse movieResponse) {
        navigator.showMovieDetail(movieResponse);
    }

    public void closeScreen() {
        navigator.close();
    }

}
