package br.com.movieapp.movie_favorite_movie.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.presentation.components.MovieFavoriteContent

@Composable
fun MovieFavoriteScreen( // função principal que representa a tela de filmes favoritos
    movies: List<Movie>, // estado da tela, que contém a lista de filmes favoritos
    navigateToDetailMovie: (Int) -> Unit // função que navega para a tela de detalhes do filme, passando o ID do filme
) {

    Scaffold( // componente Scaffold que fornece uma estrutura básica para a tela
        topBar = { // barra superior
            MovieAppBar(title = R.string.favorite_movies)
        },
        content = { paddingValues -> // conteúdo da tela
            MovieFavoriteContent( // componente que exibe a lista de filmes favoritos
                movies = movies, // lista de filmes favoritos
                paddingValues = paddingValues, // valores de preenchimento
                onClick = { movieId -> // ação de clique em um filme
                    navigateToDetailMovie(movieId) // navega para a tela de detalhes do filme
                }
            )

        }

    )

}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        movies = emptyList(),
        navigateToDetailMovie = {}
    )
}
