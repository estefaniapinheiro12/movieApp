package br.com.movieapp.movie_popula_feature_data_presentation

import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.search_movie_feature_domain_usecases.GetMovieSearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getSearchMovieUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getSearchMovieUseCase)
    }
    private val fakePagingDataSearchMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when movie search paging data`() = runTest {
        whenever(getSearchMovieUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataSearchMovies)
        )
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()
        assertThat(result).isNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when calling to the use case returns an exception`() = runTest {
        whenever(getSearchMovieUseCase.invoke(any())).thenThrow(RuntimeException())

        viewModel.fetch("")
    }

}