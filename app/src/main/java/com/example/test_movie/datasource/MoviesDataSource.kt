package com.example.test_movie.datasource

import androidx.lifecycle.MutableLiveData
import com.example.test_movie.api.Api
import com.example.test_movie.pojos.GenericHttpResponse
import com.example.test_movie.pojos.GenericResponse
import com.example.test_movie.pojos.InfoMoviesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDataSource (private val api: Api) {
    val movieNetworksState = MutableLiveData<NetworkState>()
    val movieBadResponse = MutableLiveData<GenericResponse>()
    val movieInfoSuccessResponse = MutableLiveData<InfoMoviesResponse>()

    fun requestInfoMovies(api_key: String) {
        movieNetworksState.value = NetworkState.LOADING
        api.infoGetPublication()
            .enqueue(object : Callback<InfoMoviesResponse> {
                override fun onResponse(
                    call: Call<InfoMoviesResponse>,
                    response: Response<InfoMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val gson = Gson()
                        if (response.body()!!.page==GenericHttpResponse.STATUS_OK) { //Aqui condicionamos si mi page es 1 (Que es el valor del parametro que me devuelve),
                                                                                    // mi llamada será satisfactoria
                            movieNetworksState.postValue(NetworkState.LOADED)
                            movieInfoSuccessResponse.postValue(response.body())
                        } else {
                            val badResponse =
                                gson.fromJson(
                                    gson.toJson(response.body()),
                                    GenericResponse::class.java
                                )
                            movieBadResponse.postValue(badResponse)
                            movieNetworksState.postValue(NetworkState.ERROR)
                        }
                    }
                }
                override fun onFailure(call: Call<InfoMoviesResponse>, t: Throwable) {
                    movieNetworksState.value = NetworkState.ERROR
                }
            })
    }
}