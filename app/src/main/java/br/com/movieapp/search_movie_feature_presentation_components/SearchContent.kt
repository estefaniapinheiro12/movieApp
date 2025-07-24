package br.com.movieapp.search_movie_feature_presentation_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.movie_popula_feature_data_presentation.components.MovieItem
import br.com.movieapp.search_movie_feature_presentation.MovieSearchEvent
import br.com.movieapp.ui.theme.black

@Composable
fun SearchContent(
    // Função que representa o conteúdo da tela de busca
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues, // Parâmetro para definir o espaçamento
    pagingMovies: LazyPagingItems<MovieSearch>, // Lista de filmes paginada
    query: String, // Parâmetro para a consulta de busca
    onSearch: (String) -> Unit, // Função para lidar com a busca
    onEvent: (MovieSearchEvent) -> Unit, // Função para lidar com eventos
    onDetail: (movieId: Int) -> Unit, // Função para lidar com detalhes do filme
) {

    var isLoading by remember { mutableStateOf(false) } // Variável para controlar o estado de carregamento

    Column( // Criação de uma coluna para organizar os elementos
        modifier = modifier
            .fillMaxSize() // Preenche todo o espaço disponível
            .background(black), // Define a cor de fundo como preto
        verticalArrangement = Arrangement.SpaceBetween // Organiza os elementos verticalmente com espaço entre eles
    ) {
        SearchComponents(
            query = query,
            onSearch = {
                isLoading = true // Define o estado de carregamento como verdadeiro
                onSearch(it) // Chama a função de busca com a consulta
            },
            onQueryChangeEvent = {
                onEvent(it) // Chama a função de evento com a consulta
            },
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 8.dp
            ) // Define o espaçamento horizontal e vertical
        )
        Spacer(modifier = Modifier.height(12.dp)) // Espaçador entre os componentes

        LazyVerticalGrid( // Criação de uma grade vertical para exibir os filmes
            columns = GridCells.Fixed(3), // Define o número de colunas como 3
            contentPadding = paddingValues, // Define o espaçamento interno da grade
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterHorizontally
            ), // Define o espaçamento horizontal entre os itens
            verticalArrangement = Arrangement.Center, // Define o espaçamento vertical entre os itens
            modifier = Modifier.fillMaxSize() // Preenche todo o espaço disponível
        ) {
            items(pagingMovies.itemCount) { index -> // Itera sobre os itens da lista paginada
                val movie = pagingMovies[index] // Obtém o filme na posição atual
                movie?.let { // Verifica se o filme não é nulo
                    MovieItem( // Criação do item de filme
                        voteAverage = it.voteAverage, // Avaliação do filme
                        ImageUrl = it.imageUrl, // URL da imagem do filme
                        id = it.id, // ID do filme
                        onClick = { movieId -> // Função chamada ao clicar no item
                            onDetail(movieId) // Chama a função de detalhes do filme com o ID do filme

                        }
                    )
                }
                isLoading = false // Define o estado de carregamento como falso após carregar os itens
            }
            pagingMovies.apply { // Aplica a lista paginada
                when {
                    isLoading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan) // Preenche toda a linha
                            }
                        ) {
                            LoadingView() // Exibe um item de carregamento
                        }
                    }

                    loadState.refresh is LoadState.Error -> { // estado de erro no carregamento inicial
                        isLoading = false
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
                       isLoading = false
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

