package br.com.movieapp.movie_detail_feature

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.MovieAppBar
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    // função composable que representa a tela de detalhes do filme

    uiState: MovieDetailState, // estado da tela, que contém informações sobre o filme, como detalhes, resultados, erros e estado de carregamento
    onAddFavorite: (Movie) -> Unit,
// recebe um evento do tipo GetMovieDetail, o GetMovieDetail é uma classe que representa um evento para obter os detalhes do filme
) {
    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems() // coleta os resultados de
    // filmes semelhantes como uma lista paginada, a função collectAsLazyPagingItems() é usada para
    // coletar os resultados de um fluxo de dados paginado


    Scaffold( // componente Scaffold que fornece uma estrutura básica para a tela
        topBar = { // barra superior da tela
            MovieAppBar(
                title = R.string.detail_movie
            )
        },
        content = { // conteúdo da tela
            MovieDetailContent( // componente que exibe os detalhes do filme
                movieDetails = uiState.movieDetails, // detalhes do filme
                pagingMoviesSimilar = pagingMoviesSimilar, // filmes semelhantes
                isLoading = uiState.isLoading, // estado de carregamento
                isError = uiState.error, // estado de erro
                iconColor = uiState.iconColor, // cor do ícone
                onAddFavorite = { movie -> // função chamada quando o usuário adiciona o filme aos favoritos
                    onAddFavorite(movie)
                }
            )
        }
    )
}