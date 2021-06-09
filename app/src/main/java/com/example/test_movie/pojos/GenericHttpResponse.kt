package com.example.test_movie.pojos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class GenericHttpResponse {
    companion object {
        const val STATUS_OK = 1
        const val STATUS_BAD = "BAD"
    }
}

data class GenericResponse(
    val status: Int,
    val message: String
)

@Parcelize
data class InfoMoviesResponse(

    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Results>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
) : Parcelable

@Parcelize
data class Results(

    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("genre_ids") val genre_ids: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val original_language: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int
) : Parcelable