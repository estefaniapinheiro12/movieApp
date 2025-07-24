package br.com.movieapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.movieapp.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) { // Essa
    // classe representa um item de navegação inferior

    object MoviePopular : // Esse Objeto representa a tela de filmes populares
        BottomNavItem( // O BottomNavItem é uma classe selada que representa um item de navegação
            title = "Filmes Populares", // Título do item
            icon = Icons.Default.Movie, // Ícone do item
            route = "movie_popular_screen" // Rota do item
        )

    object MovieSearch : // Esse Objeto representa a tela de pesquisa de filmes
        BottomNavItem(
            title = "Pesquisar Filmes",
            icon = Icons.Default.Search,
            route = "movie_search_screen"
        )

    object MovieFavorite : // Esse Objeto representa a tela de filmes favoritos
        BottomNavItem(
            title = "Filmes Favoritos",
            icon = Icons.Default.Favorite,
            route = "movie_favorite_screen"
        )

}


