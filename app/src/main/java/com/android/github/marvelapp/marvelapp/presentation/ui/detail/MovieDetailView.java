package com.android.github.marvelapp.marvelapp.presentation.ui.detail;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.simone.github.marvelapp.R;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieDetailView
        extends FrameLayout
        implements MovieDetailContract.View {

    Unbinder unbinder;

    @BindView(R.id.title_view)
    TextView titleView;
    @BindView(R.id.rated_view)
    TextView ratedView;
    @BindView(R.id.movie_image)
    ImageView movieImage;
    @BindView(R.id.year_view)
    TextView yearView;
    @BindView(R.id.description_view)
    TextView descriptionView;
    @BindView(R.id.root_container)
    ViewGroup rootContainer;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    public MovieDetailView(Context context) {
        super(context);
        init(context);
    }

    public MovieDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovieDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MovieDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void showMovie(final MovieResponse movie) {
        showContainer(true);

        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setTitle(movie.getTitle());
        }

        titleView.setText(movie.getTitle());
        yearView.setText(movie.getYear());
        descriptionView.setText(movie.getGenre());
        ratedView.setText((movie.getRated()));

        Glide.with(getContext())
                .load(getRandomImage(movie))
                .centerCrop()
                .into(movieImage);
    }

    private void init(Context context) {
        inflate(context, R.layout.movie_detail_view, this);
        unbinder = ButterKnife.bind(this);

        showContainer(false);
//        collapsingToolbarLayout = ButterKnife.findById(this, R.id.collapsing_toolbar);
    }

    private void showContainer(boolean show) {
        rootContainer.setVisibility(show ? VISIBLE : GONE);
    }

    private String getRandomImage(MovieResponse movie) {
        String imageUrl;
        if (movie.getPoster() != null && !movie.getPoster().isEmpty()) {
            imageUrl = movie.getPoster();
        } else {
            imageUrl = movie.getPoster();
        }
        return imageUrl;
    }

}
