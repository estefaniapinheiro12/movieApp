package br.com.movieapp.movie_favorite_movie.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with value equal to true`() =
        runTest {
            // Given
            whenever(movieFavoriteRepository.isFavorite(any()))
                .thenReturn(true)

            // When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            // Then
            Truth.assertThat(result).isEqualTo(ResultData.Success(true))
        }
    @Test
    fun `must return Success from ResultStatus when the repository returns success with value equal to false`() =
        runTest {
            // Given
            whenever(movieFavoriteRepository.isFavorite(any()))
                .thenReturn(false)

            // When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            // Then
            Truth.assertThat(result).isEqualTo(ResultData.Success(false))
        }
    @Test
    fun `must return Success from ResultStatus when the repository trows an exception`() =
        runTest {
            // Given
            val exception = RuntimeException()

            whenever(movieFavoriteRepository.isFavorite(movie.id))
                .thenThrow(exception)

            // When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movie.id)
            ).first()

            // Then
            Truth.assertThat(result).isEqualTo(ResultData.Failure(exception))
        }
}