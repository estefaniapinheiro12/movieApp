package br.com.movieapp.movie_detail_feature

import br.com.movieapp.modelDomain.Movie

sealed class MovieDetailEvent { // classe selada usada para definir eventos, uma classe selada permite
    // que você defina um número fixo de subclasses, o que é útil para representar eventos específicos
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent() // evento para obter detalhes do filme, o
// moviedId é passado como parâmetro, pois ele representa o id do filme, o MovieDetailEvent é uma classe
    // que representa um evento que pode ocorrer na tela de detalhes do filme, como obter detalhes do filme
    // ou adicionar o filme aos favoritos
    data class AddFavorites(val movie: Movie) : MovieDetailEvent() // evento para adicionar o filme aos favoritos
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent() // Verifica se um determinado filme (por id) já está marcado como favorito.
    data class RemoveFavorites(val movie: Movie) : MovieDetailEvent() // evento para remover o filme dos favoritos
}