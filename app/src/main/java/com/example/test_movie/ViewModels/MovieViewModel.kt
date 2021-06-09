package com.example.test_movie.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_movie.datasource.MoviesDataSource
import com.example.test_movie.datasource.NetworkState
import com.example.test_movie.pojos.GenericResponse
import com.example.test_movie.pojos.InfoMoviesResponse
import com.example.test_movie.pojos.Results
import com.example.test_movie.repository.Repository

class MovieViewModel(private val repository: Repository) : ViewModel() {
    private var movieDataSource: MoviesDataSource =
        repository.requestInfoMoviesDataSource()
    var networksState: MutableLiveData<NetworkState>
    var movieBadResponse: MutableLiveData<GenericResponse>
    var movieInfoSuccessResponse: MutableLiveData<InfoMoviesResponse>
    val movieFromServer = MutableLiveData<ArrayList<Results>>()

    init {
        networksState = movieDataSource.movieNetworksState
        movieBadResponse = movieDataSource.movieBadResponse
        movieInfoSuccessResponse = movieDataSource.movieInfoSuccessResponse
    }

    fun getInfoMovieUser(idUSer: String) {
        movieDataSource.requestInfoMovies(idUSer)
    }
}