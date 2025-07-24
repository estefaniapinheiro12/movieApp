package br.com.movieapp.movie_favorite_movie.domain.repository

import br.com.movieapp.modelDomain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository { // Interface: palavra-chave que define um contrato
    // (sem implementação), ou seja, uma "promessa" de que quem implementar essa interface terá que definir os métodos abaixo.

    fun getMovies(): Flow<List<Movie>> // aqui, retorna todos os filmes favoritos.
    // Flow<List<Movie>>: retorno da função — um stream reativo que emite uma lista de filmes favoritos.

    suspend fun insert(movie: Movie) // suspend: palavra-chave do Kotlin que indica que essa função
    // pode suspender a execução (precisa ser chamada dentro de uma corrotina). insert(movie: Movie): insere um filme nos favoritos.

    suspend fun delete(movie: Movie) // Remove um filme da lista de favoritos.

    suspend fun isFavorite(movieId: Int): Boolean // isFavorite(movieId: Int): verifica se o filme
    // com movieId está salvo como favorito. Retorna Boolean (verdadeiro ou falso).

}