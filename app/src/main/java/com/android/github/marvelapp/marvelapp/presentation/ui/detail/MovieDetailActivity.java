package com.android.github.marvelapp.marvelapp.presentation.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.simone.github.marvelapp.R;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieDetailActivity extends BaseActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "movie";

    public static Intent newIntent(Context context, MovieResponse movie) {
        return new Intent(context, MovieDetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    Unbinder unbinder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.movie_detail_view)
    MovieDetailView movieDetailView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setDisplayHomeAsUpEnabled(true);

        MovieResponse movie = getMovieFromIntent(getIntent());
        if (movie != null) {
            movieDetailView.showMovie(movie);
        } else {
            Log.e(TAG, "You have passed a null Movie");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private MovieResponse getMovieFromIntent(Intent intent) {
        return intent.getParcelableExtra(EXTRA_MOVIE);
    }
}
