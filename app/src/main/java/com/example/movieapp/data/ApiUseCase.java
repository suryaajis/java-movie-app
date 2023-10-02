package com.example.movieapp.data;

import com.example.movieapp.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiUseCase {
    private ApiRepository apiRepository;

    @Inject
    public ApiUseCase(ApiRepository apiRepository) { this.apiRepository = apiRepository; }

    public Single<MovieResponse> getMoviePopular() {
        return apiRepository.getApiResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
