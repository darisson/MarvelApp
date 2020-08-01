package com.android.github.marvelapp.marvelapp.presentation.ui.detail;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.ui.BasePresenter;
public interface MovieDetailContract {

    interface Presenter extends BasePresenter {

    }

    interface View {
        void showMovie(MovieResponse comic);
    }
}
