package com.javahelps.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    // List of movies for this adapter
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * This method is called by LayoutManager to create a new method.
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the parent view's context
        Context context = parent.getContext();
        // Inflate the layout_item
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        // Create a ViewHolder
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    /**
     * This method is called by LayoutManager to bind the ViewHolder with required data.
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // Get the movie at the given position
        Movie movie = movies.get(position);
        // Set the movie to the ViewHolder
        holder.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        // Return the size of the data
        return movies.size();
    }
}
