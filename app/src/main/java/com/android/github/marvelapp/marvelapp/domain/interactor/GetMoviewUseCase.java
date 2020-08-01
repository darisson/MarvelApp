package com.android.github.marvelapp.marvelapp.domain.interactor;

import com.android.github.marvelapp.marvelapp.domain.repository.MoviewRepository;
import com.android.github.marvelapp.marvelapp.data.api.entity.MovieResponse;
import com.android.github.marvelapp.marvelapp.domain.executor.PostExecutionThread;
import com.android.github.marvelapp.marvelapp.domain.executor.ThreadExecution;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import rx.Observable;

public class GetMoviewUseCase
        extends UseCase<List<MovieResponse>, GetMoviewUseCase.Params> {

    private MoviewRepository moviewRepository;

    @Inject
    public GetMoviewUseCase(MoviewRepository moviewRepository,
                            ThreadExecution threadExecution,
                            PostExecutionThread postExecutionThread) {
        super(threadExecution, postExecutionThread);
        this.moviewRepository = moviewRepository;
    }

    @Override
    protected Observable<List<MovieResponse>> buildObservable(Params params) {
        Preconditions.checkNotNull(params);
        return moviewRepository.getMovies();
    }

    public static class Params {
    }
}
