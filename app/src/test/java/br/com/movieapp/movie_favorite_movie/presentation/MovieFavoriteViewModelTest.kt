package br.com.movieapp.movie_favorite_movie.presentation

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_favorite_movie.domain.usecase.GetMoviesFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase)
    }

    private val moviesFavorite = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    @Test
    fun `must validate the data object values when calling list of favorites` () = runTest {
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(moviesFavorite)
        )
        val result = viewModel.uiState.movies.first()
        assertThat(result).isNotEmpty()
        assertThat(result).contains(moviesFavorite[0])
    }

}
