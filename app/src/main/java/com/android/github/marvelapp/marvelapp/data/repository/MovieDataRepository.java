package com.android.github.marvelapp.marvelapp.data.repository;

import com.android.github.marvelapp.marvelapp.domain.datasource.MovieDataSource;
import com.android.github.marvelapp.marvelapp.domain.repository.MoviewRepository;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class MovieDataRepository implements MoviewRepository {

    private final MovieDataSource dataSource;

    @Inject
    public MovieDataRepository(MovieDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<MovieResponse>> getMovies() {
        return dataSource.getMovies();
    }

}
