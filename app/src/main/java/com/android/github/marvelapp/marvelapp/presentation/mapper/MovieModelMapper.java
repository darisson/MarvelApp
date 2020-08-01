package com.android.github.marvelapp.marvelapp.presentation.mapper;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.android.github.marvelapp.marvelapp.presentation.viewmodel.MovieModel;
import com.android.github.marvelapp.marvelapp.domain.mapper.ModelMapper;
import com.android.github.marvelapp.marvelapp.domain.model.Movie;
import com.android.github.marvelapp.marvelapp.presentation.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class MovieModelMapper implements ModelMapper<Movie, MovieModel> {

    @Inject
    MovieModelMapper() {

    }

    @Override
    public MovieModel transform(Movie movie) {
        MovieModel viewModel = new MovieModel();
        viewModel.setId(movie.getId());
        if (!TextUtils.isEmpty(movie.getDescription())) {
            viewModel.setDescription(fromHtml(movie.getDescription()).toString());
        }
        viewModel.setImages(movie.getImages());
        viewModel.setThumbnailUrl(movie.getThumbnail());
        viewModel.setTitle(movie.getTitle());
        viewModel.setYear(movie.getYear());
        viewModel.setPrice(movie.getPrice());
        return viewModel;
    }

    @Override
    public List<MovieModel> transformCollection(Collection<Movie> fromList) {
        List<MovieModel> movieList = new ArrayList<>();
        for (Movie movie : fromList) {
            movieList.add(transform(movie));
        }
        return movieList;
    }

    @SuppressWarnings("deprecation")
    private Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
