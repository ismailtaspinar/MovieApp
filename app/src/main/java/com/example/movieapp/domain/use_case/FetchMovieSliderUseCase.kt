package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieSliderUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.fetchSliderMovies()
}