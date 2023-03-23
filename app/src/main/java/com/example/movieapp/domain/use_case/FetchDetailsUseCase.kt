package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(id : String) = movieRepository.fetchDetails(id)
}