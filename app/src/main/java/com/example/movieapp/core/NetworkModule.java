package com.example.movieapp.core;

import com.example.movieapp.data.ApiRepository;
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
import retrofit2.converter.gson.GsonConverterFactory;

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
  public static HttpLoggingInterceptor provideLoginInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Singleton
  @Provides
  public static OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Singleton
  @Provides
  public static Converter.Factory provideConverterFactory () {
    return GsonConverterFactory.create();
  }

  @Singleton
  @Provides
  public static Retrofit provideRetrofit(String baseUrl, Converter.Factory converterFactory, OkHttpClient okHttpClient) {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    return retrofit;
  }

  @Singleton
  @Provides
  public static ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }

  @Singleton
  @Provides
  public static ApiRepository provideApiRepository(ApiService apiService) {
    return new ApiRepository(apiService);
  }
}
