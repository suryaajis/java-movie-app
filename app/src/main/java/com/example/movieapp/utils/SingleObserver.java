package com.example.movieapp.utils;

import android.util.Log;

import com.example.movieapp.model.ErrorResponse;

import java.io.IOException;

import io.reactivex.observers.DisposableSingleObserver;

public abstract class SingleObserver<T> extends DisposableSingleObserver<T> {

    public abstract void noInternet();

    public abstract void onError(ErrorResponse errorResponse);

    @Override
    public void onError(Throwable e) {
        Log.e("Single Observer", "Network Error", e);
        if (e instanceof IOException) {
            noInternet();
            return;
        }
        Log.i("ERR", "onError: " + e);
    }
}
