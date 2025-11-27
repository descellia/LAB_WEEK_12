package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "00df2aa5fdddef4d5f0d63fd7b7fa9d0"
    fun fetchMovies(): Flow<List<Movie>> = flow {
        val response = movieService.getPopularMovies(apiKey)
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}