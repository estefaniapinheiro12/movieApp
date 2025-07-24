package br.com.movieapp.search_movie_feature_domain_usecases

import androidx.paging.PagingConfig
import androidx.room.util.query
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.core.domain.model.PagingSourceMoviesSearchFactory
import br.com.movieapp.search_movie_feature_domain_repository.MovieSearchRepository
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieSearchUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieSearchRepository: MovieSearchRepository

    private val movieSearchFactory = MovieSearchFactory()
        .create(poster = MovieSearchFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesSearchFactory().create(
        listOf(movieSearchFactory)
    )

    private val useCase by lazy {
        GetMovieSearchUseCaseImpl(movieSearchRepository)
    }

    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            whenever(movieSearchRepository.getSearchMovies(query = ""))
                .thenReturn(pagingSourceFake)

            val result = useCase.invoke(
                params = GetMovieSearchUseCase.Params(
                    query = "",
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            ).first()

            verify(movieSearchRepository).getSearchMovies(query = "")
            Truth.assertThat(result).isNotNull()
        }
    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method` () = runTest {

        val exception = RuntimeException()

        whenever(movieSearchRepository.getSearchMovies(query = ""))
            .thenThrow(exception)

        val result = useCase.invoke(
            params = GetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).toList()

        verify(movieSearchRepository).getSearchMovies(query = "")
        Truth.assertThat(result).isEmpty()
    }
}