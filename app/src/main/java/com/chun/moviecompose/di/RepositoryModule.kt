package com.chun.moviecompose.di

import com.chun.moviecompose.data.repository.Repository
import com.chun.moviecompose.domain.usecases.UseCases
import com.chun.moviecompose.domain.usecases.searchAllMovies.GetAllMoviesUseCase
import com.chun.moviecompose.domain.usecases.searchAllMovies.SearchAllMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllMoviesUseCase = GetAllMoviesUseCase(repository = repository),
            searchAllMoviesUseCase = SearchAllMoviesUseCase(repository = repository),
        )
    }
}
