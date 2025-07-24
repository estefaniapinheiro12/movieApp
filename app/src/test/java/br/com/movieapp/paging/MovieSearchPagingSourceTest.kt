package br.com.movieapp.paging

import androidx.paging.PagingSource
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.core.domain.model.MovieSearchPagingFactory
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.search_movie_feature_domain_source.MovieSearchRemoteDataSource
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
class MovieSearchPagingSourceTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val movieSearchFactory = MovieSearchFactory()

    private val movieSearchPagingFactory = MovieSearchPagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }
    @Test
    fun `must return a success load result when load is called`() = runTest {
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenReturn(movieSearchPagingFactory)

        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        val resultExpected = listOf(
            movieSearchFactory.create(MovieSearchFactory.Poster.Avengers),
            movieSearchFactory.create(MovieSearchFactory.Poster.JohnWick)
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
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenThrow(exception)

        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertThat(PagingSource.LoadResult.Error<Int, MovieSearch>(exception)).isEqualTo(result)
    }
}