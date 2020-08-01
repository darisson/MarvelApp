package com.android.github.marvelapp.marvelapp.presentation.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.github.marvelapp.marvelapp.presentation.ui.widget.recyclerview.EndlessRecyclerAdapter;
import com.android.simone.github.marvelapp.R;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter
        extends EndlessRecyclerAdapter<MovieResponse, MovieAdapter.ViewHolder> {

    @Inject
    public MovieAdapter() {
        super(R.layout.item_progress);
    }

    @Override
    protected ViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position, MovieResponse item) {
        holder.bindItem(item, this);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_image)
        ImageView movieView;
        @BindView(R.id.title_view)
        TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(MovieResponse movie, View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);// TODO: 08/05/17
            itemView.setTag(movie);

            titleView.setText(movie.getTitle());
            Glide.with(movieView.getContext())
                    .load(movie.getPoster())
                    .crossFade()
                    .into(movieView);


        }
    }
}
