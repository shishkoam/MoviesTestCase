package com.example.user.simplervtest.helpers;

import com.example.user.simplervtest.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 25.03.2017
 */

public enum MovieManager {
    INSTANCE;
    private Random rand = new Random();
    private ArrayList<String> imageUrlList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();

    private final static String DEFAULT_IMAGE = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg";

    public String getRandomImageUrl() {
        if (imageUrlList.isEmpty()) {
            return DEFAULT_IMAGE;
        }

        int  n = rand.nextInt(imageUrlList.size());
        return imageUrlList.get(n);
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        imageUrlList.clear();
        for (Movie movie : movieList){
            imageUrlList.add(movie.getPosterPath());
        }
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
