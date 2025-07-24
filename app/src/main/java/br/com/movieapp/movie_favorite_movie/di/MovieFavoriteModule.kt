package br.com.movieapp.movie_favorite_movie.di

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.movie_favorite_movie.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_movie.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_movie.domain.source.MovieFavoriteLocalDataSource
import br.com.movieapp.movie_favorite_movie.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_movie.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_movie.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.GetMoviesFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_movie.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao = dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(localDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(localDataSource = localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideAddMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }
}
/*💡 “Dizer ao app como criar e entregar (fornecer) as instâncias que outras partes do sistema precisam.”*/