package com.android.github.marvelapp.marvelapp.presentation.di.module;

import com.android.github.marvelapp.marvelapp.data.api.net.MarvelApiClient;
import com.android.github.marvelapp.marvelapp.data.repository.MovieDataRepository;
import com.android.github.marvelapp.marvelapp.data.repository.datasource.CloudMoviewDataSource;
import com.android.github.marvelapp.marvelapp.domain.datasource.MovieDataSource;
import com.android.github.marvelapp.marvelapp.domain.executor.JobExecutor;
import com.android.github.marvelapp.marvelapp.domain.executor.PostExecutionThread;
import com.android.github.marvelapp.marvelapp.domain.executor.ThreadExecution;
import com.android.github.marvelapp.marvelapp.domain.repository.MoviewRepository;
import com.android.github.marvelapp.marvelapp.presentation.UIThread;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    public ApplicationModule() {
    }

    @Provides
    @Singleton
    ThreadExecution provideThreadExecution(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MoviewRepository provideComicRepository(MovieDataRepository movieDataRepository) {
        return movieDataRepository;
    }

    @Provides
    @Singleton
    MovieDataSource provideComicDataSource(CloudMoviewDataSource cloudMoviewDataSource) {
        return cloudMoviewDataSource;
    }

    @Provides
    @Singleton
    MarvelApiClient provideMarvelApiClient(@Named("marvel_public_api_key") String publicKey,
                                           @Named("marvel_private_api_key") String privateKey) {
        return new MarvelApiClient(publicKey, privateKey);
    }

//    @Provides
//    @Singleton
//    ComicFlowCoordinator provideComicFlowCoordinator(ComicFlowCoordinator comicFlowCoordinator) {
//        return comicFlowCoordinator;
//    }

}
