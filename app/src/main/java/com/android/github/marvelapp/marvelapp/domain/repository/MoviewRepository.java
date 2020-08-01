package com.android.github.marvelapp.marvelapp.domain.repository;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;

import java.util.List;

import rx.Observable;
public interface MoviewRepository {

    Observable<List<MovieResponse>> getMovies();
}
