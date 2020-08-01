package com.android.github.marvelapp.marvelapp.presentation.ui.widget.recyclerview;

import android.view.View;


public interface OnItemClickListener<T> {

    void onItemClick(View view, T item);
}
