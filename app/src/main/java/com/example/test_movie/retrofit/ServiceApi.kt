package com.example.test_movie.retrofit

import com.example.test_movie.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    private var retrofit: Retrofit? = null

    fun getUserApi(): Api {
        return getClient()!!.create(
            Api::class.java
        )
    }

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
