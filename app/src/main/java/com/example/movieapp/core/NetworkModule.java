package com.example.movieapp.core;

import com.example.movieapp.data.ApiService;
import com.example.movieapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

  @Singleton
  @Provides
  public static String provideBaseUrl() {
    return Constants.BASE_URL;
  }

  @Singleton
  @Provides
  public static HttpLoggingInterceptor provideLogginInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Singleton
  @Provides
  public static Retrofit provideRetrofit(String baseUrl, Converter.Factory converterFactory, OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(converterFactory).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    return retrofit;
  }

  @Singleton
  @Provides
  public static ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }


}
