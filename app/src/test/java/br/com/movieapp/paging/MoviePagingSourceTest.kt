package br.com.movieapp.paging

import androidx.paging.LoadState
import androidx.paging.PagingSource
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.MoviePagingFactory
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_popula_feature_data_domain_source.MoviePopularRemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviePagingSourceTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MoviePopularRemoteDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val moviePagingSource by lazy {
        MoviePagingSource(remoteDataSource = remoteDataSource)
    }

    @Test
    fun `must return a sucess load result when load is called`() = runTest{
     whenever(remoteDataSource.getPopularMovies(any()))
         .thenReturn(moviePagingFactory)

        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val resultExpected = listOf(
            movieFactory.create(MovieFactory.Poster.Avengers),
            movieFactory.create(MovieFactory.Poster.JohnWick)
        )
        assertThat(PagingSource.LoadResult.Page(
            data = resultExpected,
            prevKey = null,
            nextKey = null
        )).isEqualTo(result)
    }
    @Test
    fun `must return a error load result when load is called`() = runTest {

        val exception = RuntimeException()
        whenever(remoteDataSource.getPopularMovies(any()))
            .thenThrow(exception)

        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}