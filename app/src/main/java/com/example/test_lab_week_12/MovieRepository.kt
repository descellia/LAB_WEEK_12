package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "00df2aa5fdddef4d5f0d63fd7b7fa9d0"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    suspend fun fetchMovies() {
        try {
            val response = movieService.getPopularMovies(apiKey)
            println("🔍 Total movies from API: ${response.results.size}")
            if (response.results.isEmpty()) {
                println("⚠️ Warning: API returned empty results!")
            }
            movieLiveData.postValue(response.results)
        } catch (e: Exception) {
            println("❌ API Error: ${e.message}")
            e.printStackTrace()
            errorLiveData.postValue("An error occurred: ${e.message}")
        }
    }
}