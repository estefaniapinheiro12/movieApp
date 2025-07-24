package br.com.movieapp.movie_popula_feature_data_presentation.state

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.movie_popula_feature_data_presentation.components.MovieContent
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white
import br.com.movieapp.util.UtilFunctions

@Composable
fun MoviePopularScreen( // função que representa a tela de filmes populares
    uiState: MoviePopularState, // estado da tela
    navigateToDetailMovie: (Int) -> Unit // função de navegação para detalhes do filme
) {
    val movies =
        uiState.movies.collectAsLazyPagingItems() // coleta os filmes como LazyPagingItems, que significa que eles serão carregados sob demanda


    Scaffold( // estrutura básica da tela
        topBar = {
            MovieAppBar(title = R.string.popular_movies)
        },
        content = { paddingValues -> // conteúdo da tela
            MovieContent( // conteúdo dos filmes
                pagingMovies = movies, // filmes coletados
                paddingValues = paddingValues, // valores de preenchimento
                onClick = { movieId -> // ação ao clicar em um filme
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString()) // registra o ID do filme
                    navigateToDetailMovie(movieId) // navega para os detalhes do filme

                }
            )
        }
    )
}