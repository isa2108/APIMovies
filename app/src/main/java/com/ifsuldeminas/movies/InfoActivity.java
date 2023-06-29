package com.ifsuldeminas.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoActivity extends AppCompatActivity {
    TextView filmName, filmYear, filmDirector, filmPlot, filmCountry, filmWebsite, filmRuntime;
    ImageView posterFilm;
    String name, year, director, poster, plot, country, website, runtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        filmName = findViewById(R.id.filmNameInInfo);
        filmYear = findViewById(R.id.filmYearInInfo);
        filmDirector = findViewById(R.id.filmDirectorInInfo);
        filmPlot = findViewById(R.id.filmPlotInInfo);
        filmCountry = findViewById(R.id.filmCountryInInfo);
        filmWebsite = findViewById(R.id.filmWebsiteInInfo);
        posterFilm = findViewById(R.id.posterFilmInInfo);
        filmRuntime = findViewById(R.id.runtime_info);

        name = getIntent().getStringExtra("name");
        year = getIntent().getStringExtra("year");
        director = getIntent().getStringExtra("director");
        poster = getIntent().getStringExtra("poster");
        plot = getIntent().getStringExtra("plot");
        country = getIntent().getStringExtra("country");
        website = getIntent().getStringExtra("website");
        runtime = getIntent().getStringExtra("runtime");


        filmName.setText(name);
        filmYear.setText(year);
        filmDirector.setText(director);
        filmPlot.setText(plot);
        filmCountry.setText(country);
        filmWebsite.setText(website);
        filmRuntime.setText(runtime);

        Picasso.with(this).load(poster).into(posterFilm);
    }
}
