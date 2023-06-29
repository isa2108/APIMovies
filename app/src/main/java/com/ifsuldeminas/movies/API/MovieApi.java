package com.ifsuldeminas.movies.API;

import com.ifsuldeminas.movies.Model.MovieDetails;
import com.ifsuldeminas.movies.Model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/")
    Call<SearchResult> searchMovies(@Query("s") String searchTerm);

    @GET("/")
    Call<MovieDetails> getMovieDetails(@Query("i") String imdbId);


}
