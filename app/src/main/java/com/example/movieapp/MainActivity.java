package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initObserver();
    initViewModel();
    initView();
  }

  private void initObserver() {
//    viewModel.getMovieData().observe(this, response -> {
//      Log.i("TAG MAIN", "initObserver: " + response);
//    });
  }

  private void initViewModel() {
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.doGetMoviePopular();
  }

  private void initView() {

  }


}