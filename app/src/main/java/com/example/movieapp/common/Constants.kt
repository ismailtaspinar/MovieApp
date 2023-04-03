package com.example.movieapp.common

import com.example.movieapp.BuildConfig

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = BuildConfig.API_KEY
    const val SLIDER = "movie/now_playing"
    const val LIST = "movie/upcoming"
    const val DETAILS = "movie/{movie_id}"
    const val BASE_IMAGE = "https://image.tmdb.org/t/p/w154"
    const val BASE_IMAGE_SLIDER = "https://image.tmdb.org/t/p/w1280"
}