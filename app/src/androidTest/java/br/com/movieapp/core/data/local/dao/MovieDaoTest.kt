package br.com.movieapp.core.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.movieapp.core.data.local.MovieDatabase
import br.com.movieapp.core.data.local.entity.MovieEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var movieDao: MovieDao

    @Inject
    @Named("test_db")
    lateinit var database: MovieDatabase

    @Before
    fun setup() {
        hiltRule.inject()
        movieDao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_getMovies_should_return_list_of_movies() = runTest {
        val movies = movieDao.getMovies().first()

        assertThat(movies.size).isEqualTo(0)
    }

    @Test
    fun test_getMovies_should_return_movies_ordered_by_id() = runTest {
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Homem de Ferro 1", imageUrl = "Url1"),
            MovieEntity(movieId = 2, title = "Homem de Ferro 2", imageUrl = "Url2"),
            MovieEntity(movieId = 3, title = "Homem de Ferro 3", imageUrl = "Url3"),
            MovieEntity(movieId = 4, title = "Homem de Ferro 4", imageUrl = "Url4")
        )
        movieDao.insertMovies(moviesEntities)

        val movies = movieDao.getMovies().first()

        assertThat(movies.size).isEqualTo(4)
        assertThat(movies[0].movieId).isEqualTo(1)
        assertThat(movies[1].movieId).isEqualTo(2)
        assertThat(movies[2].movieId).isEqualTo(3)
        assertThat(movies[3].movieId).isEqualTo(4)
    }

    @Test
    fun test_getMovie_should_return_movie_by_id() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de Ferro 1", imageUrl = "Url1")
        movieDao.insertMovie(movieEntity)
        val movies = movieDao.getMovies().first()
        val movieClick = movies[0]

        val movieId = movieDao.getMovie(movieClick.movieId)

        assertThat(movieId?.title).isEqualTo(movieClick.title)
    }

    @Test
    fun test_insertMovies_should_insret_movies_sucessfully() = runTest {
        val moviesEntities = listOf(
            MovieEntity(movieId = 1, title = "Homem de Ferro 1", imageUrl = "Url1"),
            MovieEntity(movieId = 2, title = "Homem de Ferro 2", imageUrl = "Url2"),
            MovieEntity(movieId = 3, title = "Homem de Ferro 3", imageUrl = "Url3"),
            MovieEntity(movieId = 4, title = "Homem de Ferro 4", imageUrl = "Url4")
        )
        movieDao.insertMovies(moviesEntities)

        val insertMovies = movieDao.getMovies().first()

        assertThat(moviesEntities.size).isEqualTo(insertMovies.size)
        assertThat(moviesEntities.containsAll(moviesEntities))
    }

    @Test
    fun test_insertMovie_should_insert_movie_sucessfully() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de Ferro 1", imageUrl = "Url1")
        movieDao.insertMovie(movieEntity)

        val insertMovie = movieDao.getMovies().first()

        val movies = movieDao.getMovies().first()
        assertThat(movies[0].title).isEqualTo(movieEntity.title)
    }

    @Test
    fun test_isFavorite_should_return_favorite_movie_when_movie_is_marked_as_favorite() = runTest {
        val movieId = 5321
        val favoriteMovie = MovieEntity(
            movieId = movieId,
            title = "Homem de Ferro 1",
            imageUrl = "Url1",
        )
        movieDao.insertMovie(favoriteMovie)

        val result = movieDao.isFavorite(movieId)

        assertThat(result).isEqualTo(favoriteMovie)
    }

    @Test
    fun test_isFavorite_should_return_null_when_movie_is_not_marked_as_favorite() = runTest {
        val movieId = 5321

        val result = movieDao.isFavorite(movieId)

        assertThat(result).isNull()
    }

    @Test
    fun test_update_should_update_a_movie_succesfully() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de ferro 1", imageUrl = "Url1")
        movieDao.insertMovie(movieEntity)
        val allMovies = movieDao.getMovies().first()
        val updateMovie = allMovies[0].copy(title = "Homem aranha")

        movieDao.insertMovie(updateMovie)

        val movies = movieDao.getMovies().first()
        assertThat(movies[0].title).contains(updateMovie.title)
    }

    @Test
    fun test_deleteMovie_should_delete_a_movie_succesfully() = runTest {
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de ferro 1", imageUrl = "Url1")
        movieDao.insertMovie(movieEntity)
        val allMovies = movieDao.getMovies().first()

        movieDao.deleteMovie(movieEntity)

        val movies = movieDao.getMovies().first()
        assertThat(movies).isEmpty()
    }
}

