package com.example.movieapp.di

import com.example.movieapp.data.source.MovieService
import com.example.movieapp.data.source.RemoteDataSourceImpl
import com.example.movieapp.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(service: MovieService) : RemoteDataSource = RemoteDataSourceImpl(service)
}