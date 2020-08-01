package com.android.github.marvelapp.marvelapp.presentation.ui.list;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.github.marvelapp.marvelapp.presentation.ui.widget.recyclerview.EndlessScrollListener;
import com.android.simone.github.marvelapp.R;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.presentation.di.ComponentProvider;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MovieListView
        extends FrameLayout
        implements MovieListContract.View {

    @Inject
    @Named("comic_per_page")
    int moviePerPage;

    private Unbinder unbinder;

    @BindView(R.id.recycler_view)
    RecyclerView movieRecyclerView;
    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;
    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    @Inject
    MovieListPresenter presenter;
    @Inject
    MovieAdapter moviesAdapter;

    OnMovieClickListener onMovieClickListener;
    EndlessScrollListener endlessScrollListener;
    GridLayoutManager layoutManager;
    List<MovieResponse> list = new ArrayList();

    public void updateList(String query){
        this.moviesAdapter.upDateListAdapter(presenter.performFiltering(query, list));
    }

    public MovieListView(Context context) {
        super(context);
        init(context);
    }

    public MovieListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MovieListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MovieListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initInjector();
        initRecyclerView();
        initRetryBtn();
        initAdapter();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initPresenter();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (unbinder != null) {
            unbinder.unbind();
        }
        presenter.destroy();
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(VISIBLE);
        movieRecyclerView.setVisibility(GONE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(GONE);
        movieRecyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void showRetry() {
        showErrorView();
    }

    @Override
    public void hideRetry() {
        hideRetryView();
    }

    @Override
    public void showEmpty() {
        showEmptyView();
    }

    @Override
    public void hideEmpty() {
        hideEmptyView();
    }

    @Override
    public void showMovieList(List<MovieResponse> movieList) {
        endlessScrollListener.setAvailableItemCount(movieList.size());
        moviesAdapter.addAll(movieList);
        moviesAdapter.setIsLoading(false);
        this.list = moviesAdapter.getItems();
    }

    @Override
    public void showMovieDetail(MovieResponse comic) {
        if (onMovieClickListener != null) {
            onMovieClickListener.onMovieClick(comic);
        }
    }


    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    private void init(Context context) {
        inflate(context, R.layout.movie_list_view, this);
        unbinder = ButterKnife.bind(this);
    }

    private void initInjector() {
        ComponentProvider.provideComicComponent(ComponentProvider.provideApplicationComponent((Activity)getContext()))
                .inject(this);
    }

    private void initPresenter() {
        presenter.bindView(this);
        presenter.start();
    }

    private void initRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_span));

        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initEndlessScrollListener(layoutManager, moviePerPage);
    }

    private void initAdapter() {
        moviesAdapter.setOnItemClickListener((view, item) -> presenter.onMovieClicked(item));
        movieRecyclerView.setAdapter(moviesAdapter);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.isProgressView(position) ? layoutManager.getSpanCount() : 1;
            }
        });
    }

    private void initEndlessScrollListener(RecyclerView.LayoutManager layoutManager, int comicPerPage) {
        endlessScrollListener = new EndlessScrollListener(layoutManager, comicPerPage) {
            @Override
            public void onLoadMore() {
                moviesAdapter.setIsLoading(true);
                presenter.loadNewPage();
            }
        };
        movieRecyclerView.addOnScrollListener(endlessScrollListener);
    }

    private void initRetryBtn() {
        btnRetry.setOnClickListener(v -> presenter.retry());
    }

    private void showEmptyView() {
        movieRecyclerView.setVisibility(GONE);
        btnRetry.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        emptyView.setText(getContext().getString(R.string.no_results));
    }

    private void showErrorView() {
        movieRecyclerView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        btnRetry.setVisibility(VISIBLE);
        emptyView.setText(getContext().getString(R.string.ops_something_went_wrong_retry));
    }

    private void hideEmptyView() {
        emptyView.setVisibility(GONE);
    }

    private void hideRetryView() {
        emptyView.setVisibility(GONE);
        btnRetry.setVisibility(GONE);
    }

    private void showRecyclerView() {
        movieRecyclerView.setVisibility(VISIBLE);
    }
}
