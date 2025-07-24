package br.com.movieapp.presentation.navigation

import br.com.movieapp.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class DetailScreenNav(val route: String) {
    object DetailScreen : DetailScreenNav(
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY=" + // route significa que essa é a rota usada para navegar
                "{$MOVIE_DETAIL_ARGUMENT_KEY}" // Esse Objeto representa a tela de detalhes do filme
    ) {
        fun passMovieId(movieId: Int) =
            // Essa função recebe um ID de filme e retorna a rota com o ID do filme
            "movie_detail_destination?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
    }
}