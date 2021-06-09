package com.example.test_movie.repository

import com.example.test_movie.api.Api
import com.example.test_movie.datasource.MoviesDataSource

class Repository(private val api: Api) {

    fun requestInfoMoviesDataSource(): MoviesDataSource {
        return MoviesDataSource(api)
    }
}