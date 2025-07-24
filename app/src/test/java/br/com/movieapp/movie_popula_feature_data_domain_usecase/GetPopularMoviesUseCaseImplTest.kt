package br.com.movieapp.movie_popula_feature_data_domain_usecase

import androidx.paging.PagingConfig
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.PagingSourceMoviesFactory
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_popula_feature_data_domain_repository.MoviePopularRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var moviePopularRepository: MoviePopularRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movie)
    )

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(moviePopularRepository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called `() =
        runTest {
            whenever(moviePopularRepository.getPopularMovies())
                .thenReturn(pagingSourceFake)

            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            ).first()

            verify(moviePopularRepository).getPopularMovies()
            Truth.assertThat(result).isNotNull()
        }
    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {

            val exception = RuntimeException()
            whenever(moviePopularRepository.getPopularMovies())
                .thenThrow(exception)

            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

           val resultList = result.toList()
            verify(moviePopularRepository).getPopularMovies()
            Truth.assertThat(resultList).isEmpty()
        }

}