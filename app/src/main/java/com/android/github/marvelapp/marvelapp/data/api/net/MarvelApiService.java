package com.android.github.marvelapp.marvelapp.data.api.net;

import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MarvelApiService {

    @GET("/saga")
    Call<List<MovieResponse>> getMovies();
}
