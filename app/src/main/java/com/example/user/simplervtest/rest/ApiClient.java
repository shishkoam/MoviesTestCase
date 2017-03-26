package com.example.user.simplervtest.rest;

import com.example.user.simplervtest.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 25.03.2017
 */

public class ApiClient {

    private final static String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private final static String API_KEY = "174c3ad63be2a483bf1b01c4c26ecf48";

    private static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void getTopRatedMovies(Callback<MoviesResponse> callback){
        ApiInterface apiService = getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(callback);
    }
}
