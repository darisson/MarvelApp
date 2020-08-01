package com.android.github.marvelapp.marvelapp.domain.datasource;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;

import java.util.List;

import rx.Observable;

public interface MovieDataSource {

    Observable<List<MovieResponse>> getMovies();
}
