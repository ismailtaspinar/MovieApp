package com.example.movieapp.di

import com.example.movieapp.common.Constants.BASE_URL
import com.example.movieapp.data.source.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private var client : OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest : Request = chain.request().newBuilder().build()
        chain.proceed(newRequest)
    }.build()

    @Provides
    @Singleton
    fun provideService() : MovieService = Retrofit.Builder().client(client).baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(MovieService::class.java)
}