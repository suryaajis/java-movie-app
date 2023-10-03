package com.example.movieapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.data.ApiRepository;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.utils.SingleObserver;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private ApiRepository apiRepository;

    @Inject
    public MainViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
    public MutableLiveData<String> errorResponse = new MutableLiveData<>();
    private CompositeDisposable disposable;

    public void doGetMoviePopular() {
        disposable.add(apiRepository.getMoviePopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                        new SingleObserver<MovieResponse>() {
                            @Override
                            public void onSuccess(MovieResponse movieResponse) {
                                Log.i("TAG", "onSuccess: " + movieResponse);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("TAG", "onError: " + e.toString());
                            }
                        }
                )
        );
    }

    public MutableLiveData<MovieResponse> getMovieData() {
        return movieData;
    }
}
