package br.com.movieapp.movie_popula_feature_data_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.modelDomain.Movie

@Composable
fun MovieDetailSimilarMovies( // função que representa a tela de filmes semelhantes
    pagingMoviesSimiliar: LazyPagingItems<Movie>, modifier: Modifier // modificador para personalizar a tela
) {
    LazyVerticalGrid( // grid vertical para exibir os filmes
        columns = GridCells.Fixed(3), // define o número de colunas, que é 3
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally), // organiza os itens horizontalmente com espaçamento
        verticalArrangement = Arrangement.Center, // organiza os itens verticalmente no centro
        modifier = modifier
    ) {
        items(pagingMoviesSimiliar.itemCount) { index -> // itens do grid
            val movie = pagingMoviesSimiliar[index] // obtém o filme na posição atual
            movie?.let { // verifica se o filme não é nulo
                MovieItem( // componente que representa um item de filme
                    voteAverage = it.voteAverage, // média de votos do filme
                    ImageUrl = it.imageUrl, // URL da imagem do filme
                    id = it.id, // ID do filme
                    onClick = { // ação de clique


                    }
                )

            }
        }
        pagingMoviesSimiliar.apply { // aplica o estado de carregamento
            when { // verifica o estado de carregamento
                loadState.refresh is LoadState.Loading -> { // se o estado de carregamento for Loading
                    item(span = { // adiciona um item ao grid
                        GridItemSpan(maxLineSpan) // define o número máximo de linhas
                    }) {
                        LoadingView() // exibe a tela de carregamento
                    }
                }

                loadState.append is LoadState.Loading -> { // se o estado de carregamento for Loading
                    item(span = { // adiciona um item ao grid
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error -> { // se o estado de carregamento for Error
                    val error = pagingMoviesSimiliar.loadState.refresh as LoadState.Error // obtém o erro
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(message = error.error.message.toString()) { // exibe a tela de erro
                            retry() // tenta novamente
                        }
                    }
                }

                loadState.append is LoadState.Error -> { // se o estado de carregamento for Error
                    val error = pagingMoviesSimiliar.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen( // exibe a tela de erro
                            message = error.error.message.toString(),
                            retry = { retry() }) // tenta novamente
                    }
                }
            }
        }
    }
}


