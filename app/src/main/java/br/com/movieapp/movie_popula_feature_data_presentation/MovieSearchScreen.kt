package br.com.movieapp.movie_popula_feature_data_presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.movie_popula_feature_data_presentation.state.MovieSearchState
import br.com.movieapp.search_movie_feature_presentation.MovieSearchEvent
import br.com.movieapp.search_movie_feature_presentation_components.SearchContent
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState, // estado da tela
    onEvent: (MovieSearchEvent) -> Unit, // evento que será disparado
    onFetch: (String) -> Unit, // função para buscar filmes
    navigateToDetailMovie: (Int) -> Unit, // função para navegar para a tela de detalhes do filme
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems() // coleta os filmes paginados

    Scaffold( // estrutura básica da tela
        topBar = { // barra superior
            MovieAppBar(title = R.string.search_movies)
        },
        content = { paddingValues -> // conteúdo da tela
            SearchContent( // componente de busca
                paddingValues = paddingValues, // valores de preenchimento
                pagingMovies = pagingMovies, // filmes paginados
                query = uiState.query, // consulta
                onSearch = { // função de busca
                    onFetch(it) // busca os filmes
                },
                onEvent = { // evento, esse evento é disparado quando o usuário clica em um filme
                    onEvent(it)// dispara o evento
                },
                onDetail = { movieId -> // evento de detalhes
                    navigateToDetailMovie(movieId) // navega para a tela de detalhes do filme
                }

            )

        }
    )

}
