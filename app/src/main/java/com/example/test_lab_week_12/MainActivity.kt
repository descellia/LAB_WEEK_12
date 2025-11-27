package com.example.test_lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.test_lab_week_12.model.Movie
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
            }
        })

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)
        recyclerView.adapter = movieAdapter

        val movieRepository = (application as MovieApplication).movieRepository
        val movieViewModel = MovieViewModel(movieRepository)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    movieViewModel.popularMovies.collect { movies ->
                        val currentYear =
                            java.util.Calendar.getInstance().get(java.util.Calendar.YEAR).toString()
                        val filteredAndSorted = movies
                            .filter { movie ->
                                movie.releaseDate?.startsWith(currentYear) == true
                            }
                            .sortedByDescending { it.popularity }
                        movieAdapter.addMovies(filteredAndSorted)
                    }
                }
            }
        }
    }
}