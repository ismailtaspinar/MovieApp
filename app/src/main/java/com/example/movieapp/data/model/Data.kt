package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("id") val id : String?,
    @SerializedName("overview") val overview : String,
    @SerializedName("poster_path") val image : String?,
    @SerializedName("release_date") val release_date : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("vote_average") val vote_average : String?,
)