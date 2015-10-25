package com.javahelps.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // RecyclerView user interface component
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private RecyclerView.Adapter<MovieViewHolder> adapter;

    // LayoutManager for the RecyclerView
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the UI components
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Arrange the items linearly
        this.layoutManager = new LinearLayoutManager(this);

        // Get the movies
        List<Movie> movies = getMovies();

        // Create the MovieAdapter
        this.adapter = new MovieAdapter(movies);

        // Set the LayoutManager
        this.recyclerView.setLayoutManager(layoutManager);

        // Set the Adapter
        this.recyclerView.setAdapter(adapter);
    }

    /**
     * Create a List of Movies.
     */
    private List<Movie> getMovies() {
        // Create a List of movies
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Star Wars", "J.J. Abrams"));
        movies.add(new Movie("The Martian", "Ridley Scott"));
        movies.add(new Movie("Crimson Peak", "Guillermo del Toro"));
        movies.add(new Movie("Pan", "Joe Wright"));
        movies.add(new Movie("Knock Knock", "Eli Roth"));
        movies.add(new Movie("Sicario", "Denis Villeneuve"));
        movies.add(new Movie("The Walk", "Robert Zemeckis"));
        movies.add(new Movie("Black Mass", "Scott Cooper"));
        movies.add(new Movie("Goosebumps", "Rob Letterman"));
        movies.add(new Movie("Dope", "Rick Famuyiwa"));

        return movies;
    }
}
