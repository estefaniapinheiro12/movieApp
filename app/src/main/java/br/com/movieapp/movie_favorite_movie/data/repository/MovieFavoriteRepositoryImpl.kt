package br.com.movieapp.movie_favorite_movie.data.repository

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_movie.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor( // class: declara uma nova classe chamada.
// MovieFavoriteRepositoryImpl: ...que é a implementação concreta do repositório de filmes favoritos.
    // @Inject constructor(...): isso permite que o Dagger/Hilt saiba como instanciar essa classe, injetando a dependência localDataSource.
    private val localDataSource: MovieFavoriteLocalDataSource // uma variável privada, passada pelo construtor, que representa a fonte local de dados.
): MovieFavoriteRepository { // : MovieFavoriteRepository: indica que essa classe implementa a interface MovieFavoriteRepository.
    override fun getMovies(): Flow<List<Movie>> { // override: sobrescreve a função definida na interface.
        // fun getMovies(): função pública que retorna os filmes favoritos. : Flow<List<Movie>>: o retorno é um fluxo de uma lista de filmes — ideal para observar mudanças.
        return localDataSource.getMovies() // delega a chamada para o data source local.
    }

    override suspend fun insert(movie: Movie) { // suspend: marca essa função como suspensa — pode ser chamada de forma assíncrona com coroutines.
        // insere um filme nos favoritos.
       localDataSource.insert(movie) // delega a lógica à camada de dados local.
    }

    override suspend fun delete(movie: Movie) { // Mesma lógica: função suspensa que remove um filme da lista de favoritos.
        localDataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean { // Verifica se um filme (pelo id) está marcado como favorito.
        // Retorna um Boolean com o resultado.
        return localDataSource.isFavorite(movieId)
    }
}