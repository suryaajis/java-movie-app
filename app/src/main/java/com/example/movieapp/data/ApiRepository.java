package com.example.movieapp.data;

import com.example.movieapp.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiRepository {
    private final ApiService apiService;

    @Inject
    public ApiRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<MovieResponse> getMoviePopular() {
        return apiService.getMoviePopular();
    }
}
