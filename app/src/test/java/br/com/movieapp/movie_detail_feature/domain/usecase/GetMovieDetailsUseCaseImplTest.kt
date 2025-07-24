package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieDetailsFactory
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.PagingSourceMoviesFactory
import br.com.movieapp.movie_detail_feature.domain.repository.MoviesDetailsRepository
import br.com.movieapp.util.ResultData
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseImplTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailRepository: MoviesDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailFactory = MovieDetailsFactory()
        .create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movieFactory)
    )
    private val getMovieDetailUseCase by lazy {
        GetMovieDetailsUseCaseImpl(movieDetailRepository)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            whenever(movieDetailRepository.getMovieDetails(movieId = movieFactory.id))
                .thenReturn(movieDetailFactory)

            whenever(movieDetailRepository.getMovieSimiliar(movieId = movieFactory.id))
                .thenReturn(pagingSourceFake)

            val result = getMovieDetailUseCase.invoke(
                GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )
            verify(movieDetailRepository).getMovieDetails(movieFactory.id)
            verify(movieDetailRepository).getMovieSimiliar(movieFactory.id)

            Truth.assertThat(result).isNotNull()
            Truth.assertThat(result is ResultData.Success).isTrue()
        }

    @Test
    fun `should return Error from ResultStatus when get movieDetails requests return error`() = runTest {

        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
            .thenThrow(exception)

        val result = getMovieDetailUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )
        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        Truth.assertThat(result is ResultData.Failure).isTrue()
    }
    @Test
    fun `should return a ResultStatus error when getting similiar movies returns an error`() = runTest {

        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieSimiliar(movieFactory.id))
            .thenThrow(exception)

        val result = getMovieDetailUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        verify(movieDetailRepository).getMovieSimiliar(movieFactory.id)
        Truth.assertThat(result is ResultData.Failure).isTrue()
    }
}