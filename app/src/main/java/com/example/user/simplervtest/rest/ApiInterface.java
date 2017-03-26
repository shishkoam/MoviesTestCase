package com.example.user.simplervtest.rest;

import com.example.user.simplervtest.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 25.03.2017
 */

interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
