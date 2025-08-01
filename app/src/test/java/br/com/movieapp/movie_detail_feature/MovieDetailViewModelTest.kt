package br.com.movieapp.movie_detail_feature

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieDetailsFactory
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.util.ResultData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieDetailsUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.Avengers)
        )
    )
    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieDetailsUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }

    @Test
    fun `must notify uiState with Success when get movies similar and movie details returns sucess`() =
        runTest {
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(ResultData.Success(true)))

            val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
            assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)

            val movieDetails = viewModel.uiState.movieDetails
            val results = viewModel.uiState.results
            assertThat(movieDetails).isNotNull()
            assertThat(results).isNotNull()
        }

    @Test
    fun `must notify uiState with Failure when get movies details and returns exception`() =
        runTest {

            val exception = Exception("Um erro ocorreu")
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(ResultData.Failure(exception))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(ResultData.Failure(exception)))

            viewModel.uiState.isLoading

            val error = viewModel.uiState.error
            assertThat(exception.message).isEqualTo(error)
        }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is checked` () = runTest {
        whenever(deleteMovieDetailsUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        viewModel.onAddFavorite(movie = movie)

        verify(deleteMovieDetailsUseCase).invoke(deleteArgumentCaptor.capture())
        assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState with filled favorite icon when current icon is unchecked` () = runTest {
        whenever(addMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))

        val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        viewModel.onAddFavorite(movie = movie)

        verify(addMovieFavoriteUseCase).invoke(addArgumentCaptor.capture())
        assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    fun `must notify uiState with bookmark icon filled in if check returns true` () = runTest {

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        viewModel.uiState.isLoading

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)

    }

    fun `must notify uiState with bookmark icon filled in if check returns false` () = runTest {
        
        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))

        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        viewModel.uiState.isLoading

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)

    }
}