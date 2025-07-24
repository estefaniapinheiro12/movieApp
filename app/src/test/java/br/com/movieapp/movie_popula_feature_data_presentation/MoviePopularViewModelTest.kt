package br.com.movieapp.movie_popula_feature_data_presentation

import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_popula_feature_data_domain_usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviePopularViewModelTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling paging data from movies`() = runTest {

        whenever(getPopularMoviesUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataMovies)
        )
        val result = viewModel.uiState.movies.first()

        assertThat(result).isNull()
    }
    @Test(expected = RuntimeException::class)
    fun `must throw an exception when calling to the use case returns an exception`() = runTest {
        whenever(getPopularMoviesUseCase.invoke(any())).thenThrow(RuntimeException())

        val result = viewModel.uiState.movies.first()

        assertThat(result).isNull()
    }
}

