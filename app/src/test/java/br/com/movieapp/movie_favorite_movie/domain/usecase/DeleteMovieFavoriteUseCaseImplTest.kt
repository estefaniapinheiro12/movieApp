package br.com.movieapp.movie_favorite_movie.domain.usecase

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import com.google.common.truth.Truth
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
class DeleteMovieFavoriteUseCaseImplTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val deleteMovieFavoriteUseCase by lazy {
        DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }
    @Test
    fun `should return Success from ResultStatus when the repository returns success equal to unit` () = runTest {

        whenever(movieFavoriteRepository.delete(movie)).thenReturn(Unit)

        val result = deleteMovieFavoriteUseCase.invoke(
            DeleteMovieFavoriteUseCase.Params(movie = movie)
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `should return Failure from ResultStatus when the repository returns an exception`() = runTest {

        val exception = RuntimeException()
        whenever(movieFavoriteRepository.insert(movie))
            .thenThrow(exception)

        val result = deleteMovieFavoriteUseCase.invoke(
            params = DeleteMovieFavoriteUseCase.Params(
                movie = movie
            )
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Failure(exception))
    }

}