package com.ifsuldeminas.movies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ifsuldeminas.movies.InfoActivity;
import com.ifsuldeminas.movies.Model.Model;
import com.ifsuldeminas.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Model> movies;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(List<Model> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_items, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Model movie = movies.get(position);

        Picasso.with(context).load(movie.getPoster()).into(holder.poster);
        holder.name.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.desc.setText(movie.getPlot());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView name, year, desc;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            name = itemView.findViewById(R.id.filmName);
            year = itemView.findViewById(R.id.filmYear);
            desc = itemView.findViewById(R.id.filmDescription);
        }
    }
}
