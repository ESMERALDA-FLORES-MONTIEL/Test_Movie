package com.example.test_movie.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_movie.repository.Repository

class MovieViewModelFactory(private var repository: Repository) : ViewModelProvider.Factory {
    private val movieRepository: Repository = repository
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}