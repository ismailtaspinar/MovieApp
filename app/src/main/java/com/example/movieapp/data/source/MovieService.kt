package com.example.movieapp.data.source

import com.example.movieapp.common.Constants.DETAILS
import com.example.movieapp.common.Constants.LIST
import com.example.movieapp.common.Constants.SLIDER
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(LIST)
    suspend fun fetchMovie(@Query("api_key") api_key : String,@Query("page") page: Int) : Response

    @GET(SLIDER)
    suspend fun fetchSliderMovie(@Query("api_key") api_key : String) : Response

    @GET(DETAILS)
    suspend fun fetchDetails(@Path("movie_id") id : String,@Query("api_key") api_key : String) : Movie
}