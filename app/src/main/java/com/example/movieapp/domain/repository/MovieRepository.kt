package com.example.movieapp.domain.repository


import com.example.movieapp.common.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovie(page : Int) : Flow<Resource<Response>>

    fun fetchSliderMovies() : Flow<Resource<Response>>

    fun fetchDetails(id : String) : Flow<Resource<Movie>>
}