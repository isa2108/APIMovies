package com.ifsuldeminas.movies.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResult {
    @SerializedName("Search")
    private List<Model> movies;

    public List<Model> getMovies() {
        return movies;
    }
}
