package com.example.movieapp.data.source

import com.example.movieapp.common.Constants.API_KEY
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response
import com.example.movieapp.domain.source.RemoteDataSource

class RemoteDataSourceImpl(private val service : MovieService) : RemoteDataSource {
    override suspend fun fetchMovies(page : Int): Response {
        return service.fetchMovie(API_KEY,page)
    }

    override suspend fun fetchSliderMovies(): Response {
        return service.fetchSliderMovie(API_KEY)
    }

    override suspend fun fetchDetails(id: String): Movie {
        return service.fetchDetails(id,API_KEY)
    }


}