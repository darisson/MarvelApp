package com.android.github.marvelapp.marvelapp.data.repository.datasource;

import com.android.github.marvelapp.marvelapp.data.api.net.MarvelApiClient;
import com.android.github.marvelapp.marvelapp.data.api.net.MarvelApiService;
import com.android.github.marvelapp.marvelapp.domain.datasource.MovieDataSource;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.data.api.exception.MarvelApiException;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

@Singleton
public class CloudMoviewDataSource implements MovieDataSource {

    private final MarvelApiService apiService;
    private final int moviesLimit;

    @Inject
    public CloudMoviewDataSource(MarvelApiClient apiClient,
                                 @Named("comic_per_page") int moviesLimit) {
        this.apiService = apiClient.createService(MarvelApiService.class);
        this.moviesLimit = moviesLimit;
    }

    @Override
    public Observable<List<MovieResponse>> getMovies() {
        return Observable.create(new Observable.OnSubscribe<List<MovieResponse>>() {
            @Override
            public void call(Subscriber<? super List<MovieResponse>> subscriber) {
                try {

                    Call<List<MovieResponse>> call = apiService.getMovies();

                    Response<List<MovieResponse>> response = call.execute();

                    if (response.isSuccessful()) {
                        subscriber.onNext(response.body());
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new MarvelApiException(response.code(), ""));
                    }

                } catch (Exception e) {
                    subscriber.onError(new MarvelApiException(e.getMessage(), e.getCause()));
                }
            }
        });
    }
}
