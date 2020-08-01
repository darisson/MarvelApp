package com.android.github.marvelapp.marvelapp.presentation.ui.list;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.ui.BasePresenter;

import java.util.List;

public interface MovieListContract {

    interface Presenter extends BasePresenter {
        void loadNewPage();

        void retry();

        void onMovieClicked(MovieResponse model);
    }

    interface View {
        void showLoading();

        void hideLoading();

        void showEmpty();

        void hideEmpty();

        void showRetry();

        void hideRetry();

        void showMovieList(List<MovieResponse> movieList);

        void showMovieDetail(MovieResponse model);
    }
}
