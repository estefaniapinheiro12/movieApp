package br.com.movieapp.movie_favorite_movie.presentation.state

import br.com.movieapp.modelDomain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState( // data classe que representa o estado da tela
    val movies : Flow<List<Movie>> = emptyFlow() // val para armazenar a lista de filmes favoritos (inicialmente vazia)
)
