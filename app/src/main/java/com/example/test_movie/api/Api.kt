package com.example.test_movie.api

import com.example.test_movie.pojos.InfoMoviesResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    //@FormUrlEncoded
    @POST("movie/top_rated?api_key=93e2ba3b2b8eeac423224f7798b294a4")
    fun infoGetPublication(
        //@Field("api_key") api_key: String
    ): Call<InfoMoviesResponse>
}