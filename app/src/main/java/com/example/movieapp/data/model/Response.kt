package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("page") val page : String?,
    @SerializedName("results")val data : List<Data>,
    @SerializedName("total_pages") val total_pages : String,
    @SerializedName("total_results") val total_results : String
)
