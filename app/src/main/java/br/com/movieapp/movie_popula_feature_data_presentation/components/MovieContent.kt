package br.com.movieapp.movie_popula_feature_data_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.modelDomain.Movie

@Composable
fun MovieContent(
    modifier: Modifier = Modifier, // Modificador para o layout
    pagingMovies: LazyPagingItems<Movie>, // Lista de filmes paginada
    paddingValues: PaddingValues, // Valores de preenchimento
    onClick: (movieId: Int) -> Unit, // Função de clique para um filme
) {
    Box(modifier = modifier.background(Color.Black)) { // Define o fundo como preto
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Define 3 colunas na grade
            contentPadding = paddingValues, // Aplica os valores de preenchimento
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento horizontal entre os itens
            verticalArrangement = Arrangement.Center, // Alinhamento vertical dos itens
            modifier = Modifier.fillMaxSize() // Preenche todo o espaço disponível
        ) {
            items(pagingMovies.itemCount) { index -> // Itera sobre os itens da lista
                val movie = pagingMovies[index] // Obtém o filme na posição atual
                movie?.let { // Verifica se o filme não é nulo
                    MovieItem( // Componente que exibe as informações do filme
                        voteAverage = it.voteAverage, // Média de votos do filme
                        ImageUrl = it.imageUrl, // URL da imagem do filme
                        id = it.id, // ID do filme
                        onClick = { movieId -> // Função de clique
                            onClick(movieId) // Chama a função de clique com o ID do filme
                        }
                    )
                }
            }
            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> { // estado de carregamento inicial
                        item(
                            span = {
                                GridItemSpan(maxLineSpan) // Preenche toda a linha
                            }
                        ) {
                            LoadingView() // Exibe um item de carregamento
                        }
                    }

                    loadState.append is LoadState.Loading -> { // estado de carregamento adicional
                        item(
                            span = {
                                GridItemSpan(maxLineSpan) // Preenche toda a linha
                            }
                        ) {
                            LoadingView() // Exibe um item de carregamento
                        }
                    }

                    loadState.refresh is LoadState.Error -> { // estado de erro no carregamento inicial
                        item(
                            span = {
                                GridItemSpan(maxLineSpan) // Preenche toda a linha
                            }
                        ) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet", // Mensagem de erro
                                retry = { // Função de recarregar
                                    retry()

                                })
                        }
                    }

                    loadState.append is LoadState.Error -> { // estado de erro no carregamento adicional
                        item(
                            span = {
                                GridItemSpan(maxLineSpan) // Preenche toda a linha
                            }
                        ) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet", // Mensagem de erro
                                retry = { // Função de recarregar
                                    retry()
                                })
                        }
                    }
                }
            }
        }
    }
}