package com.android.github.marvelapp.marvelapp.presentation.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.android.github.marvelapp.marvelapp.presentation.ui.detail.MovieDetailView;
import com.android.simone.github.marvelapp.R;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity
        extends BaseActivity
        implements OnMovieClickListener {

    public static Intent newIntent(Context context) {
        return new Intent(context, MovieListActivity.class);
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_list_view)
    MovieListView movieListView;
    @BindView(R.id.search_view)
    SearchView searchMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initViews();

        searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieListView.updateList(newText);
                return false;
            }
        });
    }

    private void initViews() {
        movieListView.setOnMovieClickListener(this);
    }

    private boolean isTwoPane() {
        return ButterKnife.findById(this, R.id.movie_detail_view) != null;
    }

    @Override
    public void onMovieClick(MovieResponse movie) {
        if (isTwoPane()) {
            MovieDetailView movieDetailView = ButterKnife.findById(this, R.id.movie_detail_view);
            movieDetailView.showMovie(movie);
        } else {
            flowCoordinator.readComicDetail(movie);
        }
    }
}
