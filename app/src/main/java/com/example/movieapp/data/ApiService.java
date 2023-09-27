package com.example.movieapp.data;

import com.example.movieapp.model.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {

  @GET("movie/popular")
  Single<MovieResponse> getMoviePopular();
}
