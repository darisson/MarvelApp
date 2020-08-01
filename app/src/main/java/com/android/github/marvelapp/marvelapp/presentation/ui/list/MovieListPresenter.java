package com.android.github.marvelapp.marvelapp.presentation.ui.list;

import android.util.Log;

import com.android.github.marvelapp.marvelapp.presentation.mapper.MovieModelMapper;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.domain.interactor.GetMoviewUseCase;
import com.android.github.marvelapp.marvelapp.domain.interactor.UseCase;
import com.android.github.marvelapp.marvelapp.presentation.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

@ActivityScope
public class MovieListPresenter implements MovieListContract.Presenter {

    private static final String TAG = MovieListPresenter.class.getSimpleName();

    private int page = 0;

    @Inject
    @Named("character_id")
    String characterId;

    private final UseCase getMovieUseCase;

    private MovieListContract.View movieListView;

    @Inject
    public MovieListPresenter(UseCase useCase,
                              MovieModelMapper viewModelMapper) {
        this.getMovieUseCase = useCase;
    }

    public void bindView(MovieListContract.View view) {
        movieListView = view;
    }

    @Override
    public void start() {
        movieListView.hideEmpty();
        movieListView.hideRetry();
        movieListView.showLoading();
        getMovieList();
    }

    @Override
    public void destroy() {
        releaseRefs();
    }

    @Override
    public void loadNewPage() {
        getMovieList();
        Log.d(TAG, "loadNewPage: " + page);
    }

    @Override
    public void retry() {
        movieListView.hideRetry();
        movieListView.showLoading();
        getMovieList();
    }

    @Override
    public void onMovieClicked(MovieResponse model) {
        movieListView.showMovieDetail(model);
    }

    @SuppressWarnings("unchecked")
    private void getMovieList() {
        getMovieUseCase.execute(buildMovieListSubscriber(), new GetMoviewUseCase.Params());
    }

    private void releaseRefs() {
        getMovieUseCase.unsubscribe();
        movieListView = null;
        page = 0;
    }

    public List<MovieResponse> performFiltering(CharSequence query, List<MovieResponse> currenciesList ) {
        List<MovieResponse> filteredList = new ArrayList<MovieResponse>() ;
        Iterator<MovieResponse> interator = currenciesList.iterator();
        while (interator.hasNext()) {
            MovieResponse movieResponse = interator.next();
            if(movieResponse.getTitle().toLowerCase().contains(query.toString().toLowerCase())){
                filteredList.add(movieResponse);

            }
        }
        return filteredList;
    }



    private MovieListSubscriber buildMovieListSubscriber() {
        return new MovieListSubscriber();
    }

        private final class MovieListSubscriber extends Subscriber<List<MovieResponse>> {

        @Override
        public void onCompleted() {
            movieListView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: " + e);
            movieListView.hideLoading();
            movieListView.showRetry();
        }

        @Override
        public void onNext(List<MovieResponse> comicList) {
            movieListView.showMovieList(comicList);
        }
    }


}
