package com.example.movieapp.data.repository

import com.example.movieapp.common.Resource
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.Response
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val remoteDataSource : RemoteDataSource) : MovieRepository {
    override fun fetchMovie(page : Int): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)

        try {
            val response = remoteDataSource.fetchMovies(page)
            emit(Resource.Success(response))
        } catch (t : Throwable){
            emit(Resource.Error(t))
        }
    }

    override fun fetchSliderMovies(): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)

        try {
            val response = remoteDataSource.fetchSliderMovies()
            emit(Resource.Success(response))
        } catch (t : Throwable){
            emit(Resource.Error(t))
        }
    }

    override fun fetchDetails(id: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading)

        try {
            val movie = remoteDataSource.fetchDetails(id)
            emit(Resource.Success(movie))
        } catch (t:Throwable){
            emit(Resource.Error(t))
        }
    }

}