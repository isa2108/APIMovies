package com.ifsuldeminas.movies;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ifsuldeminas.movies.API.Controller;
import com.ifsuldeminas.movies.API.MovieApi;
import com.ifsuldeminas.movies.Adapter.MovieAdapter;

import com.ifsuldeminas.movies.Model.Model;
import com.ifsuldeminas.movies.Model.MovieDetails;
import com.ifsuldeminas.movies.Model.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button addBtn;
    RecyclerView rv;
    List<Model> movies;
    MovieAdapter adapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApi api = Controller.getApi();
        addBtn = findViewById(R.id.addBtn);
        movies = new ArrayList<>();
        rv = findViewById(R.id.rv_movie);

        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter = new MovieAdapter(movies, this);
        adapter.setOnItemClickListener(position -> showMovieDetails(position));

        rv.setAdapter(adapter);

        addBtn.setOnClickListener(v -> {
            Intent i = new Intent(this, InputActivity.class);
            startActivity(i);
        });

        name = getIntent().getStringExtra("name");

        api.searchMovies(name).enqueue(new Callback<SearchResult>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
                if (response.isSuccessful()) {
                    SearchResult searchResult = response.body();
                    if (searchResult != null) {
                        movies.addAll(searchResult.getMovies());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro na resposta da API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMovieDetails(int position) {
        Model movie = movies.get(position);

        MovieApi movieDetailsApi = Controller.getApi();
        movieDetailsApi.getMovieDetails(movie.getImdbID()).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    MovieDetails movieDetails = response.body();
                    if (movieDetails != null) {
                        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                        intent.putExtra("name", movieDetails.getTitle());
                        intent.putExtra("year", movieDetails.getYear());
                        intent.putExtra("poster", movieDetails.getPoster());
                        intent.putExtra("director", movieDetails.getDirector());
                        intent.putExtra("plot", movieDetails.getPlot());
                        intent.putExtra("country", movieDetails.getCountry());
                        intent.putExtra("website", movieDetails.getWebsite());
                        intent.putExtra("runtime", movieDetails.getRuntime());
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro na resposta da API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
