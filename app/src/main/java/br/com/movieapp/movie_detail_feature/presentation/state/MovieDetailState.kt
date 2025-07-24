package br.com.movieapp.movie_detail_feature.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState( // data classe que representa o estado do detalhe do filme
    val movieDetails: MovieDetails? = null, // detalhes do filme, a classe MovieDetails é um modelo de dados, onde pega as informações do filme
    // Armazena os detalhes do filme atual (como título, sinopse, nota, etc.). Pode ser nulo enquanto os dados ainda estão sendo carregados.
    val error: String = "", // mensagem de erro, caso ocorra algum problema ao buscar os dados.
    val isLoading: Boolean = false, // booleano que representa se está carregando ou não
    val iconColor: Color = Color.White, // cor do ícone
    val results: Flow<PagingData<Movie>> = emptyFlow() // resultados da pesquisa, a classe Movie é um modelo de dados, onde pega as informações do filme
)
