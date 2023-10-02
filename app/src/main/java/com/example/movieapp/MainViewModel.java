package com.example.movieapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.data.ApiRepository;
import com.example.movieapp.data.ApiUseCase;
import com.example.movieapp.model.ErrorResponse;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.utils.SingleObserver;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private ApiRepository apiRepository;
    private ApiUseCase apiUseCase;
    public MutableLiveData<MovieResponse> movies = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();
    private CompositeDisposable disposable;

    @Inject
    public MainViewModel(ApiRepository apiRepository) { this.apiRepository = apiRepository; }

    public void getMoviePopular() {
        disposable.add(apiUseCase.getMoviePopular().subscribeWith(new SingleObserver<MovieResponse>() {
            @Override
            public void onSuccess(MovieResponse movieResponse) {
                movies.setValue(movieResponse);
            }

            @Override
            public void noInternet() {
                error.setValue("No Internet");
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                error.setValue("Error");
            }
        }));
    }
}
