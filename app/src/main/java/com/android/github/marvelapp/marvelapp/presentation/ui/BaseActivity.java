package com.android.github.marvelapp.marvelapp.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.github.marvelapp.marvelapp.presentation.ui.navigator.MovieFlowCoordinator;
import com.android.github.marvelapp.marvelapp.presentation.di.ComponentProvider;
import com.android.github.marvelapp.marvelapp.presentation.di.component.ApplicationComponent;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected MovieFlowCoordinator flowCoordinator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent()
                .inject(this);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            flowCoordinator.closeScreen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ComponentProvider.provideApplicationComponent(this);
    }
}
