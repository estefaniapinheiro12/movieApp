package br.com.movieapp.movie_favorite_movie.data.source

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.data.mapper.toMovieEntity
import br.com.movieapp.movie_favorite_movie.data.mapper.toMovies
import br.com.movieapp.movie_favorite_movie.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor( // implementação concreta da interface de fonte local.
    // @Inject constructor(...): diz ao framework de injeção (ex: Hilt) como criar a instância dessa classe.
    private val dao: MovieDao // private val dao: armazena o DAO como dependência injetada.
): MovieFavoriteLocalDataSource { // MovieFavoriteLocalDataSource: interface que esta classe implementa.
    override fun getMovies(): Flow<List<Movie>> { // override: sobrescreve a função da interface.
        // getMovies(): retorna a lista de filmes favoritos. : Flow<List<Movie>>: um fluxo de listas de Movie, assíncrono e observável.
        return dao.getMovies().map { // dao.getMovies(): retorna o fluxo com as entidades do banco.
            it.toMovies() // .map { it.toMovies() }: transforma cada emissão do DAO (provavelmente lista de entidades) em uma lista de Movie do domínio usando a função toMovies().
        }
    }

    override suspend fun insert(movie: Movie) { // suspend: função suspensa (usa coroutines para não travar a UI).
        // insert(movie: Movie): insere um filme no banco.
        dao.insertMovie(movie.toMovieEntity()) // movie.toMovieEntity(): converte o objeto de domínio para entidade do Room.
    }

    override suspend fun delete(movie: Movie) { // função que recebe um objeto Movie do domínio e vai deletá-lo.
        dao.deleteMovie(movie.toMovieEntity()) // chama a função correta do DAO, deleteMovie, que faz a remoção no banco.
    }

    override suspend fun isFavorite(movieId: Int): Boolean { // isFavorite(movieId: Int): verifica se um filme com o ID fornecido está na base local.
        return dao.isFavorite(movieId) != null // dao.isFavorite(movieId): consulta se o filme existe.
        // != null: retorna true se o filme estiver no banco (ou seja, é favorito), false caso contrário.
    }
}