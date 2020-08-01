package com.android.github.marvelapp.marvelapp.presentation.di.module;

import com.android.github.marvelapp.marvelapp.domain.interactor.UseCase;
import com.android.github.marvelapp.marvelapp.domain.interactor.GetMoviewUseCase;
import com.android.github.marvelapp.marvelapp.presentation.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    public UseCaseModule() {
    }

    @Provides
    @ActivityScope
    UseCase provideGetComicListUseCase(GetMoviewUseCase useCase) {
        return useCase;
    }
}
