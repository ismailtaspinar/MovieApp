package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path") val backdrop_path : String?,
    @SerializedName("homepage") val homepage : String?,
    @SerializedName("overview") val overview : String,
    @SerializedName("release_date") val date : String,
    @SerializedName("title") val title : String,
    @SerializedName("vote_average") val vote : Float
)
