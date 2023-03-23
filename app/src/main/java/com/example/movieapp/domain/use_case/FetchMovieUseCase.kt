package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieUseCase @Inject constructor(private val movieRepository: MovieRepository)  {
    operator fun invoke(page : Int) = movieRepository.fetchMovie(page)
}