package br.com.movieapp.paging

import androidx.paging.PagingSource
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.MoviePagingFactory
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSimiliarPagingSourceTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieDetailsRemoteDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val moviesSimilarPagingSource by lazy {
        MovieSimiliarPagingSource(
            movieId = 1,
            remoteDataSource = remoteDataSource
        )
    }
    @Test
    fun `must return a success load result when load is called`() = runTest {
        whenever(remoteDataSource.getMoviesSimilar(any(), any()))
            .thenReturn(moviePagingFactory)

        val result = moviesSimilarPagingSource.load(
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
        Truth.assertThat(PagingSource.LoadResult.Page(
            data = resultExpected,
            prevKey = null,
            nextKey = null
        )).isEqualTo(result)
    }
    @Test
    fun `must return a error load result when load is called`() = runTest {

        val exception = RuntimeException()
        whenever(remoteDataSource.getMoviesSimilar(any(), any()))
            .thenThrow(exception)

        val result = moviesSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        Truth.assertThat(PagingSource.LoadResult.Error<Int, MovieSearch>(exception)).isEqualTo(result)
    }

}