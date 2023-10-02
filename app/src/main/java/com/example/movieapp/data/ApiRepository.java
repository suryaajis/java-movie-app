package com.example.movieapp.data;

import com.example.movieapp.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepository {
    private ApiService apiService;

    @Inject
    public ApiRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<MovieResponse> getApiResponse() {
        return apiService.getMoviePopular();
    }
}
