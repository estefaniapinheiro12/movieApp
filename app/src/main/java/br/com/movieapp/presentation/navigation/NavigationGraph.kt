package br.com.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.movie_detail_feature.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.MovieDetailViewModel
import br.com.movieapp.movie_favorite_movie.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_movie.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popula_feature_data_presentation.MoviePopularViewModel
import br.com.movieapp.movie_popula_feature_data_presentation.MovieSearchScreen
import br.com.movieapp.movie_popula_feature_data_presentation.MovieSearchViewModel
import br.com.movieapp.movie_popula_feature_data_presentation.state.MoviePopularScreen
import br.com.movieapp.search_movie_feature_presentation.MovieSearchEvent
import br.com.movieapp.util.Constants

@Composable
fun NavigationGraph(navHostController: NavHostController) { // Essa função cria o gráfico de navegação, dentro do parâmtro dela, temos o navHostController que é o controlador de navegação
    NavHost(
        // Essa função cria o host de navegação, o host é o container que contém as telas
        navController = navHostController, // Esse parâmetro é o controlador de navegação
        startDestination = BottomNavItem.MoviePopular.route, // Define a tela inicial como a tela de filmes populares, e inicia com ela,
        // startDestination: Parâmetro que define qual rota será exibida primeiro, quando o NavHost for mostrado.
    ) {
        composable(BottomNavItem.MoviePopular.route) { // Essa função define a tela de filmes populares
            val viewModel: MoviePopularViewModel =
                hiltViewModel() // Cria uma instância do ViewModel de filmes populares
            val uiState = viewModel.uiState // Obtém o estado da tela do ViewModel
            MoviePopularScreen( // Essa função representa a tela de filmes populares
                uiState = uiState, // Estado da tela
                navigateToDetailMovie = { // Essa função navega para a tela de detalhes do filme
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it)) // o navHostController serve para navegar entre as telas, navega para a tela de detalhes do filme,
                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) { // Essa função define a tela de pesquisa de filmes

            val viewModel: MovieSearchViewModel =
                hiltViewModel() // Cria uma instância do ViewModel de pesquisa de filmes, o hiltViewModel é uma função do Hilt que fornece uma instância do ViewModel
            val uiState = viewModel.uiState // Obtém o estado da tela do ViewModel
            val onEvent: (MovieSearchEvent) -> Unit =
                viewModel::event // Cria uma função que dispara eventos no ViewModel
            val onFetch: (String) -> Unit =
                viewModel::fetch // Cria uma função que busca filmes no ViewModel

            MovieSearchScreen( // Essa função representa a tela de pesquisa de filmes
                uiState = uiState, // Estado da tela
                onEvent = onEvent, // Função de evento
                onFetch = onFetch, // Função de busca
                navigateToDetailMovie = { // Essa função navega para a tela de detalhes do filme
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }
        composable(BottomNavItem.MovieFavorite.route) { // Essa função define a tela de filmes favoritos
            val viewModel: MovieFavoriteViewModel = hiltViewModel() // Cria uma instância do ViewModel de filmes favoritos
            val uiState = viewModel.uiState.movies
                .collectAsStateWithLifecycle(initialValue = emptyList()) // Obtém o estado da tela do ViewModel

            MovieFavoriteScreen( // Essa função representa a tela de filmes favoritos
              movies = uiState.value, // Estado da tela
                navigateToDetailMovie = { // Essa função navega para a tela de detalhes do filme
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                    // navHostController serve para navegar entre as telas, navega para a tela de detalhes do filme,
                }
            )
        }
        composable( // Essa função define a tela de detalhes do filme
            route = DetailScreenNav.DetailScreen.route, // Essa função define a rota da tela de detalhes do filme
            arguments = listOf( // Essa função define os argumentos da tela de detalhes do filme
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType // Define o tipo do argumento como inteiro
                    defaultValue = 0 // Define o valor padrão do argumento como 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel() // Cria uma instância do ViewModel de detalhes do filme
            val uiState = viewModel.uiState // Obtém o estado da tela do ViewModel
            val onAddFavorite = viewModel::onAddFavorite
            MovieDetailScreen( // Essa função representa a tela de detalhes do filme
                uiState = uiState, // Estado da tela
                onAddFavorite = onAddFavorite // Função de adicionar/remover favorito
            )
        }
    }
}