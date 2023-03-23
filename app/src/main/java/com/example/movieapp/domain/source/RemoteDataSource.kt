package com.example.movieapp.domain.source

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response

interface RemoteDataSource {
    suspend fun fetchMovies(page : Int) : Response

    suspend fun fetchSliderMovies() : Response

    suspend fun fetchDetails(id : String) : Movie
}